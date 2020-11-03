package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class Saver {
    public static void writeFile(String path, IModel model){
        try{
            Writer writer;
            if (!path.endsWith(".txt")) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + ".txt"), "utf-8"));
            }
            else {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
            }
            String save = "";
            save += writeBall(model.getBall());
            save += writeGizmos(model.getGizmos());
            save += writeAbsorbers(model.getAbsorbers());
            save += writeConnections(model);
            save += writeGravity(model.getEnvironment());
            save += writeFriction(model.getEnvironment());
            System.out.println("Save" + save);
            writer.write(save);
            writer.close();
        }
        catch(Exception e){
            System.out.println("Exception" + e.getMessage());
        }
    }

    private static String writeConnections(IModel model) {
        String c = "";
        for (IAbsorber absorber: model.getAbsorbers()) {
            c += "KeyConnect key " + absorber.getKeyConnect() + " down " + absorber.getName() + System.lineSeparator();
        }
        for (IGizmo gizmo: model.getGizmos()) {
            for (IGizmo consumer: gizmo.getConnectedGizmos()) {
                c += "Connect " + gizmo.getName() + " " + consumer.getName() + System.lineSeparator();
            }
            if (gizmo.getKeyConnect() != 0) {
                c += "KeyConnect key " + gizmo.getKeyConnect() + " down " + gizmo.getName() + System.lineSeparator();
            }
        }
        c += System.lineSeparator();
        return c;
    }

    private static String writeFriction(EnvironmentInteraction env) {
        return "Friction " + env.getMU1() + " " + env.getMU2() + System.lineSeparator();
    }

    private static String writeGravity(EnvironmentInteraction env) {
        return "Gravity " + env.getGravity() + System.lineSeparator();
    }

    private static String writeAbsorbers(List<IAbsorber> list) {
        String gs = "";
        for (IAbsorber absorber: list){
            gs += "Absorber " + absorber.getName() + " " + (int)absorber.getTopLeftPoint().getX()/Constants.L + " " + (int)absorber.getTopLeftPoint().getY()/Constants.L + " " + (int)absorber.getBotRightPoint().getX()/Constants.L + " " + (int)absorber.getBotRightPoint().getY()/Constants.L + System.lineSeparator();
        }
        gs += System.lineSeparator();
        return gs;
    }

    private static String writeGizmos(List<IGizmo> list) {
        String gs = "";
        for (IGizmo gizmo: list){
            int x;
            if(gizmo.getType().equals("RightFlipper")){
                x = (int)gizmo.getLocation().getX()/Constants.L - 1;
            }
            else {
                x = (int)gizmo.getLocation().getX()/Constants.L;
            }
            gs += gizmo.getType() + " " + gizmo.getName() + " " + x + " " + (int)gizmo.getLocation().getY()/Constants.L + System.lineSeparator();
            if (gizmo.getType().equals("Triangle") || gizmo.getType().equals("RightFlipper") || gizmo.getType().equals("LeftFlipper")) {
                for (int i = 0; i < gizmo.rotations(); i++) {
                    gs += "Rotate " + gizmo.getName() + System.lineSeparator();
                }
            }
        }
        gs += System.lineSeparator();
        return gs;
    }

    private static String writeBall(IBall ball) {
        String b = "";
        if (ball != null) {
            b = "Ball B1 " + ball.getPoint().getX()/Constants.L + " " + ball.getPoint().getY()/Constants.L + " " + ball.getVelocity().x() + " " + ball.getVelocity().y() + System.lineSeparator() + System.lineSeparator();
        }
        return b;
    }
}
