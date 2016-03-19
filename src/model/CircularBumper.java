package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;

public class CircularBumper extends AStationaryGizmo {

	private int radius;
	
	/* Collections used to optimise up game performance */
	/** The visual representation of the Gizmo. Used by drawing code to determine 
	 * what a Gizmo will look like on screen. **/
	private Shape drawingShape;
	/** A set of Circles belonging to this Gizmo. They act as collision detectors
	 * with a ball, often at the edges of a shape. **/
	protected Set<Circle> circleSet = new HashSet<Circle>();

	public CircularBumper(String name, int grid_tile_x, int grid_tile_y, Color color) {
		super(name, grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L, color);
		
		radius = 10;
		

		// Collection-speed up initialisation
		circleSet = new HashSet<Circle>();
		updateCollections();
	}



/* Ball's Collection-sped up methods (Circle, DrawingShape) */
	@Override
	public Shape getDrawingShape() {
		return drawingShape;
	}
	
	private void setupDrawingShape() {
		drawingShape = new Ellipse2D.Double(getX(), getY(), MainEngine.L, MainEngine.L);
	}
	
	@Override
	public Set<Circle> getCircles() {
		return circleSet;
	}

	private void setupCircles() {
		circleSet.clear();
		Circle circle = new Circle(getX() + 10, getY() + 10, 10);
		circleSet.add(circle);
	}
	
	private void updateCollections() {
		setupDrawingShape();
		setupCircles();
	}

	
	

/* Regular methods implementation */
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#triggerAction()
	 */
	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#rotate(int)
	 */
	@Override
	public boolean rotate(int degree) {
		// Circle shouldn't be rotatable so this method doesn't need to do anything
		return true;
	}

	/* (non-Javadoc)
	 * @see model.AGizmoComponent#move(int, int)
	 */
	@Override
	public void move(int grid_tile_x, int grid_tile_y) {
		// TODO Validation
		
		super.move(grid_tile_x * MainEngine.L, grid_tile_y * MainEngine.L);
		
		updateCollections();
	}
	
	/* (non-Javadoc)
	 * @see model.AGizmoComponent#reset()
	 */
	@Override
	public void reset() {
		setColour(getInitialColour());
	}
	
	
/* Overwritting methods */
	@Override
	public String toString(){
		return "Circle " + super.toString();
	}
	

/* CircularBumper exclusive methods */
	public double getRadius() {
		return radius;
	}


}
