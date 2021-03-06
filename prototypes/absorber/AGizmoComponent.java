package absorber;

import java.awt.Color;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;

abstract public class AGizmoComponent {

	/* The following attributes can be defined before the Gizmo is created **/
	/** A String given to a Gizmo to identity it    **/
	private String gizmoID;
	/** The horizontal-coordinate of the component (in pixel). Along with y-coordinate, this determines the position of a Gizmo 
	 * component on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int xpos;
	/** The vertical-coordinate of the component (in pixel). Along with y-coordinate, this determines the position of a Gizmo 
	 * component on the board. Where exactly it is on the component depends on the type of Gizmo.    **/
	private int ypos;
	/** Colour of the Gizmo, used by the view to determine what colour to paint    **/
	private Color colour;
	/** Whether the Gizmo is visible on the board or not */
	private boolean visibility;
	
	/** The visual representation of the Gizmo. Used by drawing code to determine what a Gizmo will look like on screen **/
	protected Shape drawingShape;
	/** A set of Circles belonging to this Gizmo. They act as collision detectors with a ball **/
	protected Set<Circle> circleSet;
	
	/* The following attributes have default values for each Gizmo components (will be defined automatically in sub-classes)
	 * or will be modified in the constructor of sub-classes */
	/** The angle of rotation of a Gizmo. In this project, the default position of a Gizmo component is treated as 0; with
	 * the exception of Line, which will automatically be calculated properly upon construction **/
	private int rotationAngle;

	public AGizmoComponent(String name, int x, int y, Color color) {
		gizmoID = name;
		xpos = x;
		ypos = y;
		this.colour = color;
		
		setupDrawingShape();
		circleSet = new HashSet<Circle>();

		// Default settings
		visibility = true;
		rotationAngle = 0;
	}

	/**
	 * Action that is executed when a Ball has a collision with this component, or from a connection of triggers. 
	 * Keep in mind there are no graphical procedures here.
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	abstract public void triggerAction();
	
	/**
	 * The setup of the Shape implementation: used to specify how the Gizmo's outline will look on the board
	 * @modify this
	 * @effect Setup the Shape (drawingShape)
	 */
	abstract protected void setupDrawingShape();

	public Shape getDrawingShape(){
		return drawingShape;
	}
	
	/**
	 * The setup of the Circle collection. Circle is a Physics class used for collision detection, and is needed
	 * for proper corner Line Segment detection. Gizmo components should set up their Circle objects here.
	 * @modify this
	 * @effect Fill the collection (circleSet) which hold all the Circles in this class with appropriate objects
	 */
	abstract protected void setupCircles();
	
	public Set<Circle> getCircles(){
		return circleSet;
	}

	public String getGizmoID() {
		return gizmoID;
	}
	
	public boolean setX(int x) {
		// TODO some validation
		xpos = x;
		return true;
	}

	public int getX() {
		return xpos;
	}
	
	public boolean setY(int y) {
		// TODO some validation
		ypos = y;
		return true;
	}

	public int getY() {
		return ypos;
	}

	public boolean setColour(Color color) {
		// TODO some validation
		this.colour = color;
		return true;
	}

	public Color getColour() {
		return colour;
	}
	
	/**
	 * Method called when the user decides to rotate a Gizmo component.
	 * @param degree - the angle of rotation to add
	 * @modify this
	 * @effect varies with each Gizmo component; see individual class
	 */
	public boolean rotate(int degree) {
		// no need for this in the prototype!
		return true;
	}
	
	public int getRotation() {
		return rotationAngle;
	}
	
	public void setVisibility(boolean visible) {
		this.visibility = visible;
	}

	public boolean getVisibility() {
		return visibility;
	}

}
