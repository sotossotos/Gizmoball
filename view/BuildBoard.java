package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import model.Constants;

public class BuildBoard{
	
	private JPanel buttonPanel;
	private JPanel gizmoButtonPanel;
	private JPanel gizmoActionPanel;
	private JPanel connectorButtonPanel;
	private JPanel triggerEnvElementsPanel;
	private GamePanel gamePanel;
	private JPanel container;
	private ButtonGroup bGroup;
	JComboBox triggerOptions;
	JSlider frictionSlider;
	JSlider gravitySlider;
	private boolean isVisible;
	
	public BuildBoard() {
		container = new JPanel();
		bGroup = new ButtonGroup();
		gamePanel = new GridPanel();
		createButtonPanel();
		createGamePanel();
		JSplitPane pane = new JSplitPane();
		pane.setLeftComponent(buttonPanel);
		pane.setRightComponent(gamePanel);
		container.add(pane);
	}
	
	public JPanel getBoardView() {
		return container;
	}
	
	private void createButtonPanel() {
		buttonPanel = new JPanel(new GridLayout(4,1));
		
		createGizmoButtonPanel();
		buttonPanel.add(gizmoButtonPanel);
		
		createGizmoActionPanel();
		buttonPanel.add(gizmoActionPanel);
		
		createConnectorButtonPanel();
		buttonPanel.add(connectorButtonPanel);
		
		createTriggerEnvElementsPanel();
		buttonPanel.add(triggerEnvElementsPanel);
		
	}
	
	private void createGizmoButtonPanel() {
		gizmoButtonPanel = new JPanel(new GridLayout(3,3));
		
		JButton bSquare = new JButton("Square");
		JButton bTriangle = new JButton("Triangle");
		JButton bCircle = new JButton("Circle");
		JButton bAbsorber = new JButton("Absorber");
		JButton bLFlipper = new JButton("Left Flipper");
		JButton bRFlipper = new JButton("Right Flipper");
		JButton bBall = new JButton("Ball");
		JButton bBallVelocity = new JButton("Set Ball Velocity");
		
		bSquare.setActionCommand("Square");
		bTriangle.setActionCommand("Triangle");
	    bCircle.setActionCommand("Circle");
		bAbsorber.setActionCommand("Absorber");
		bLFlipper.setActionCommand("LeftFlipper");
		bRFlipper.setActionCommand("RightFlipper");
		bBall.setActionCommand("Ball");
		bBallVelocity.setActionCommand("SetVelocity");
		
		bGroup.add(bSquare);
		bGroup.add(bTriangle);
		bGroup.add(bCircle);
		bGroup.add(bLFlipper);
		bGroup.add(bRFlipper);
		bGroup.add(bAbsorber);
		bGroup.add(bBall);
		bGroup.add(bBallVelocity);
		
		gizmoButtonPanel.add(bSquare);
		gizmoButtonPanel.add(bTriangle);
		gizmoButtonPanel.add(bCircle);
		gizmoButtonPanel.add(bAbsorber);
		gizmoButtonPanel.add(bLFlipper);
		gizmoButtonPanel.add(bRFlipper);
		gizmoButtonPanel.add(bBall);
		gizmoButtonPanel.add(bBallVelocity);
		
		gizmoButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		bSquare.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bSquare.getModel(), true);
			}});
		bTriangle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bGroup.setSelected(bTriangle.getModel(), true);				
			}});
		bCircle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bGroup.setSelected(bCircle.getModel(), true);				
			}});
		bLFlipper.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bGroup.setSelected(bLFlipper.getModel(), true);				
			}});
		bRFlipper.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bGroup.setSelected(bRFlipper.getModel(), true);				
			}});
		bAbsorber.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				bGroup.setSelected(bAbsorber.getModel(), true);		
			}});
		bBall.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bBall.getModel(), true);
			}
			
		});
		bBallVelocity.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bBallVelocity.getModel(), true);
				
			}
			
		});
		
	}
	
	private void createGizmoActionPanel() {
		gizmoActionPanel = new JPanel(new GridLayout(1,3));
		
		JButton bMove = new JButton("Move");
		JButton bDelete = new JButton("Delete");
		JButton bRotate = new JButton("Rotate");
		
		bMove.setActionCommand("Move");
		bDelete.setActionCommand("Delete");
		bRotate.setActionCommand("Rotate");
		
		bGroup.add(bMove);
		bGroup.add(bDelete);
		bGroup.add(bRotate);
		
		bMove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bMove.getModel(), true);
			}});
		bDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bDelete.getModel(), true);
			}});
		bRotate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bRotate.getModel(), true);
			}});
		
		gizmoActionPanel.add(bRotate);
		gizmoActionPanel.add(bMove);
		gizmoActionPanel.add(bDelete);
		gizmoActionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void createConnectorButtonPanel() {
		connectorButtonPanel = new JPanel(new GridLayout(2,2));
		
		JButton bConnect = new JButton("Connect");
		JButton bDisconnect = new JButton("Disconnect");
		JButton bKeyConnect = new JButton("Key Connect");
		JButton bKeyDisconnect= new JButton("Key Disconnect");
		
		bConnect.setActionCommand("Connect");
		bDisconnect.setActionCommand("Disconnect");
		bKeyConnect.setActionCommand("KeyConnect");
		bKeyDisconnect.setActionCommand("KeyDisconnect");
		
		bGroup.add(bConnect);
		bGroup.add(bDisconnect);
		bGroup.add(bKeyConnect);
		bGroup.add(bKeyDisconnect);
		
		bConnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bConnect.getModel(), true);
			}});
		bDisconnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bDisconnect.getModel(), true);
			}});
		bKeyConnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bKeyConnect.getModel(), true);
			}});
		bKeyDisconnect.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bKeyDisconnect.getModel(), true);
			}});
		
		
		
		connectorButtonPanel.add(bKeyConnect);
		connectorButtonPanel.add(bKeyDisconnect);
		connectorButtonPanel.add(bConnect);
		connectorButtonPanel.add(bDisconnect);
		connectorButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	private void createTriggerEnvElementsPanel() {
		triggerEnvElementsPanel = new JPanel(new GridLayout(4,1));
		
		String[] triggers = {"Change color Red", "Change color Green", "Change color Blue", "Rotate" };

		triggerOptions = new JComboBox(triggers);
		triggerOptions.setSelectedIndex(0);
		
		JButton bSetTrigger= new JButton("Set Trigger Action");
		bSetTrigger.setActionCommand("setTrigger");
		
		bSetTrigger.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bGroup.setSelected(bSetTrigger.getModel(), true);
			}});
		
		
		
		gravitySlider = new JSlider(JSlider.HORIZONTAL);
		gravitySlider.setName("Gravity Slider");
		
		frictionSlider = new JSlider(JSlider.HORIZONTAL);
		gravitySlider.setName("Friction Slider");
		
		triggerEnvElementsPanel.add(triggerOptions);
		triggerEnvElementsPanel.add(bSetTrigger);
		triggerEnvElementsPanel.add(gravitySlider);
		triggerEnvElementsPanel.add(frictionSlider);
	}
	
	private void createGamePanel() {
		gamePanel = new GridPanel();
		gamePanel.setPreferredSize(new Dimension(Constants.L*20, Constants.L*20));		
	}
	public JSlider getGravitySlider()
	{
		return gravitySlider;
	}
	public JSlider getFrictionSlider()
	{
		return frictionSlider;
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public ButtonGroup getBGroup() {
		return bGroup;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
	
	public void setVisibility(boolean isVisible) 
	{
		this.isVisible = isVisible;
	}
	
	public JComboBox getTriggerAction()
	{
		return triggerOptions;
		
	}

}
