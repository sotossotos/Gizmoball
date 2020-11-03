package model;
import java.awt.Point;
import java.io.File;
import java.util.Scanner;

import physics.Vect;
public class Loader {
  
    public static boolean parseFile(String filename, IModel model){
        File file = new File(filename);
        boolean valid = false;
        try {
            Scanner scan = new Scanner(file);
            Scanner line;
            String ballName = null;
            valid = true;
            while(scan.hasNextLine()){
                line = new Scanner(scan.nextLine());
                if (line.hasNext()) {
                    
                    if (!parseCommand(line, model, ballName)) {
                        valid = false;
                    }
                }
            }
            scan.close();
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
        }
        return valid;
    }
    
    private static boolean parseCommand(Scanner line, IModel model, String ballName) {
        String command = line.next();
        boolean valid = false;
        if (command.equals("Triangle") || command.equals("Square") || command.equals("Circle") 
                || command.equals("RightFlipper") || command.equals("LeftFlipper")) {
            valid = makeGizmo(command, line, model);
        }
        else if (command.equals("Ball")) {
            valid = makeBall(line, model, ballName);
        }
        else if (command.equals("Absorber")) {
            valid = makeAbsorber(line, model);
        }
        else if (command.equals("Rotate")) {
            valid = doRotation(line, model);
        }
        else if (command.equals("Delete")) {
            valid = doDeletion(line, model, ballName);
        }
        else if (command.equals("Move")) {
            valid = doMovement(line, model, ballName);
        }
        else if (command.equals("Connect")) {
            valid = makeConnection(line, model);
        }
        else if (command.equals("KeyConnect")) {
            valid = makeKeyConnection(line, model);
        }
        else if (command.equals("KeyConnect")) {
            valid = makeConnection(line, model);
        }
        else if (command.equals("Gravity")) {
            valid = setGravity(line, model);
        }
        else if (command.equals("Friction")) {
            valid = setFriction(line, model);
        }
        return valid;
    }
    
    private static boolean makeGizmo(String command, Scanner line, IModel model) {
        if (line.hasNext()) {
            String name = line.next();
            if (line.hasNextInt()) {
                int x = line.nextInt() * Constants.L;
                if (line.hasNextInt()) {
                    int y = line.nextInt() * Constants.L;
                    if (!line.hasNext()) {
                    	if(command.equals("RightFlipper"))	
                    		x+=Constants.L;
                        Point pos = new Point(x, y);
                        if (model.addGizmo(pos, command)) {
                            model.getGizmoAtLocation(pos).setName(name);
                            return true;
                        }
                        
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean setFriction(Scanner line, IModel model) {
        if (line.hasNextFloat()){
            float mu1 = line.nextFloat();
            if (line.hasNextFloat()){
                float mu2 = line.nextFloat();
                if (!line.hasNext()) {
                    model.getEnvironment().setFriction(mu1, mu2);
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean setGravity(Scanner line, IModel model) {
        if (line.hasNextFloat()){
            float g = line.nextFloat();
            if (!line.hasNext()) {
                model.getEnvironment().setGravity(g);
                return true;
            }
        }
        return false;
    }
    
    private static boolean makeConnection(Scanner line, IModel model) {
        if (line.hasNext()) {
            String producer = line.next();
            if (line.hasNext()) {
                String consumer = line.next();
                if (!line.hasNext()) {
                    if (model.getGizmo(producer) != null && model.getGizmo(consumer) != null) {
                        IGizmo g1 = model.getGizmo(producer);
                        IGizmo g2 = model.getGizmo(consumer);
                        g1.addConnectedGizmo(g2);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean makeKeyConnection(Scanner line, IModel model){
        if (line.hasNext()) {
            if (line.next().equals("key")) {
                if (line.hasNextInt()) {
                    int num = line.nextInt();
                    if (line.hasNext()) {
                        String act = line.next();
                        if (act.equals("down") || act.equals("up")) {
                            if (line.hasNext()){
                                String name = line.next();
                                if (!line.hasNext()) {
                                    if (model.getGizmo(name) != null) {
                                        model.getGizmo(name).setKeyConnect(num);
                                        return true;
                                    }
                                    else if (model.getAbsorber(name) != null) {
                                        model.getAbsorber(name).setKeyConnect(num);
                                        return true;
                                    }
                                }
                            }
                                
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean doMovement(Scanner line, IModel model, String ballName) {
        if (line.hasNext()) {
            String name = line.next();
            if (ballName.equals(name) || model.getAbsorber(name) != null || model.getGizmo(name) != null) {
                if (line.hasNextInt()) {
                    int x = line.nextInt() * Constants.L;
                    if (line.hasNextInt()) {
                        int y = line.nextInt() * Constants.L;
                        if (!line.hasNext()) {
                            Point pos = new Point(x, y);
                            if (model.getAbsorber(name) != null) {
                                int x2 = x + model.getAbsorber(name).getBotRightPoint().x - model.getAbsorber(name).getTopLeftPoint().x;
                                int y2 = y + model.getAbsorber(name).getBotRightPoint().y - model.getAbsorber(name).getTopLeftPoint().y;
                                Point pos2 = new Point(x2, y2);
                                Point oldTL = model.getAbsorber(name).getTopLeftPoint();
                                Point oldBR = model.getAbsorber(name).getBotRightPoint();
                                model.removeAbsorber(model.getAbsorber(name).getTopLeftPoint());
                                if (!model.addAbsorber(pos, pos2)){
                                    model.addAbsorber(oldTL, oldBR);
                                    model.getAbsorber(oldTL).setName(name);
                                }
                                else {
                                    model.getAbsorber(pos).setName(name);
                                    return true;
                                }
                                
                            }
                            else if (model.getGizmo(name) != null) {
                                return model.moveGizmo(model.getGizmo(name).getLocation(), pos);
                            }
                        }
                    }
                }
                else if (line.hasNextFloat()) {
                    float x = line.nextFloat() * Constants.L;
                    if (line.hasNextFloat()) {
                        float y = line.nextFloat() * Constants.L;
                        if (!line.hasNext()) {
                            if (ballName.equals(name)) {
                                Point xy = new Point();
                                xy.setLocation(x, y);
                                Point oldXY = model.getBall().getPoint();
                                Vect velocity = model.getBall().getVelocity();
                                model.removeBall();
                                model.addBall(xy, Constants.BALLRADIUS);
                                if (model.getBall() != null) {
                                    model.getBall().setVelocity(velocity);
                                    return true;
                                }
                                else {
                                    model.addBall(oldXY, Constants.BALLRADIUS);
                                    model.getBall().setVelocity(velocity);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean doDeletion(Scanner line, IModel model, String ballName) {
        if (line.hasNext()) {
            if (!line.hasNext()) {
                String name = line.next();
                if (model.getAbsorber(name) != null){
                    model.removeAbsorber(model.getAbsorber(name).getTopLeftPoint());
                    return true;
                }
                else if (model.getGizmo(name) != null) {
                    model.removeGizmo(model.getGizmo(name).getLocation());
                    return true;
                }
                else if (name.equals(ballName)) {
                    model.removeBall();
                    return true;
                }
            }
            
        }
        return false;
    }
    
    private static boolean doRotation(Scanner line, IModel model) {
        if (line.hasNext()) {
        	String name = line.next();
            if (!line.hasNext()) {
                
                if (model.getGizmo(name) != null) {
                	System.out.println("Actual rotate inside loader");
                    model.rotateGizmo(model.getGizmo(name).getLocation());
                    return true;
                }
                else {
                    System.out.println("fail");
                }
                
            }
            
        }
        return false;
    }
    
    private static boolean makeAbsorber(Scanner line, IModel model) {
        if (line.hasNext()) {
            String name = line.next();
            if (line.hasNextInt()) {
                int x1 = line.nextInt() * Constants.L;
                if (line.hasNextInt()) {
                    int y1 = line.nextInt() * Constants.L;
                    if (line.hasNextInt()) {
                        int x2 = line.nextInt() * Constants.L;
                        if (line.hasNextInt()) {
                            int y2 = line.nextInt() * Constants.L;
                            if (!line.hasNext()) {
                                Point pos1 = new Point(x1, y1);
                                Point pos2 = new Point(x2, y2);
                                if (model.addAbsorber(pos1, pos2)) {
                                    IAbsorber a = model.getAbsorber(pos1);
                                    a.setName(name);
                                    return true;
                                }              
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean makeBall(Scanner line, IModel model, String ballName) {
        if (line.hasNext()) {
            String name = line.next();
            if (line.hasNextFloat()) {
                float x = line.nextFloat() * Constants.L;
                if (line.hasNextFloat()) {
                    float y = line.nextFloat() * Constants.L;
                    if (line.hasNextFloat()) {
                        float xv = line.nextFloat();
                        if (line.hasNextFloat()) {
                            float yv = line.nextFloat();
                            if (!line.hasNext()) {
                                if (ballName == null) {
                                    Point xy = new Point();
                                    xy.setLocation(x, y);
                                    model.addBall(xy,Constants.BALLRADIUS);
                                    if (model.getBall().getPoint().equals(xy)) {
                                        model.getBall().setVelocity(new Vect(xv, yv));
                                        ballName = name;
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
