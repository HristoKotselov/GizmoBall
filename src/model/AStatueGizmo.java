package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;

import physics.Circle;

/**
 * TODO
 * Class designed for Gizmos that do NOT exhibit any motion whatsoever, as if they are part of the background. Thus, their Shape 
 * & Circles will remain constant for most of the time, allowing them to be safely stored.
 * Examples:	Square Bumper, Circular Bumper
 */
abstract public class AStatueGizmo extends AGizmoComponent {

	/** The visual representation of the Gizmo. Used by drawing code to determine what a Gizmo will look like on screen. **/
	protected Shape drawingShape;
	/** A set of Circles belonging to this Gizmo. They act as collision detectors with a ball, often at the edges of a shape. **/
	protected Set<Circle> circleSet = new HashSet<Circle>();

	public AStatueGizmo(String name, int x, int y, Color color) {
		super(name, x, y, color);

		setupDrawingShape();
		setupCircles();
	}

	/**
	 * The setup of the Shape implementation: used to specify how the Gizmo's outline will look on the board
	 * @modify this
	 * @effect Setup the Shape (drawingShape)
	 */
	abstract protected void setupDrawingShape();

	/**
	 * The setup of the Circle collection. Circle is a Physics class used for collision detection, and is needed
	 * for proper corner Line Segment detection. Statue Gizmos should set up their Circle objects here.
	 * @modify this
	 * @effect Fill the collection (circleSet) which hold all the Circles in this class with appropriate objects
	 */
	abstract protected void setupCircles();

	@Override
	public Shape getDrawingShape() {
		return drawingShape;
	}

	@Override
	public Set<Circle> getCircles() {
		return circleSet;
	}
	
	abstract public void updateCollections();

}
