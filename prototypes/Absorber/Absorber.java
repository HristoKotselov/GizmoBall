package Absorber;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;

import physics.LineSegment;

public class Absorber extends AGizmoComponent implements ILineSegmentCollider{
	/** The amount of width this absorber takes up in the board (in pixel). Absorber will always at least take up 1L*1L worth of space    **/
	private int width;
	/** The amount of height this absorber takes up in the board (in pixel). Absorber will always at least take up 1L*1L worth of space    **/
	private int height;
	/** A list of Line Segments around the edge of the absorber, which will act as the collision detector with a ball **/
	private Set<LineSegment> ls;
	/** The currently captured Ball within the Absorber. If there is no ball within the absorber, then this object becomes null. */
	private Ball capturedBall;
	
	
	
	public Absorber(String name, int grid_tile_x, int grid_tile_y, int widthInL, int heightInL, Color color) {
		super(name, grid_tile_x * Model.L, grid_tile_y * Model.L, color);
		
		setWidth(widthInL * Model.L);
		setHeight(heightInL * Model.L);
		capturedBall = null;
		
		ls = new HashSet<LineSegment>();
		createLineSeg();
		// no need for this in the prototype:		setHeight( );
		// no need for this in the prototype:		 setWidth( );
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shape getShape() {
		return new Rectangle(getWidth(), getHeight());
	}

	@Override
	public void triggerAction() {
		// TODO Auto-generated method stub

	}
	
	/* Absorber's collision detector methods */
	
	/**
	 * The setup of the Line Segment collection. Each Gizmo component which rely on Line Segments for collision detection should
	 * set up their Line Segment objects here.
	 * @modify this
	 * @effect Fill the collection which hold all the Line Segments in this class with appropriate objects
	 */
	private void createLineSeg() {
		int lCorner_X = getX();
		int rCorner_X = getX() + getWidth();;
		int tCorner_Y = getY();
		int bCorner_Y = getY() + getHeight();
		
		LineSegment tlCorner_trCorner = new LineSegment(lCorner_X, tCorner_Y, rCorner_X, tCorner_Y);
		LineSegment trCorner_brCorner = new LineSegment(rCorner_X, tCorner_Y, rCorner_X, bCorner_Y);
		LineSegment brCorner_blCorner = new LineSegment(rCorner_X, bCorner_Y, lCorner_X, bCorner_Y);
		LineSegment blCorner_tlCorner = new LineSegment(lCorner_X, bCorner_Y, lCorner_X, tCorner_Y);
		
		ls.add(tlCorner_trCorner);
		ls.add(trCorner_brCorner);
		ls.add(brCorner_blCorner);
		ls.add(blCorner_tlCorner);
	}

	@Override
	public Set<LineSegment> getLineSeg() {
		return ls;
	}
	
	/* Absorber exclusive methods */
	public boolean setWidth(int newWidth) {
		this.width = newWidth;
		return true;
	}

	public int getWidth() {
		return width;
	}
	
	public boolean setHeight(int newHeight) {
		this.height = newHeight;
		return true;
	}

	public int getHeight() {
		return height;
	}
	
	public void setBall(Ball b) {
		capturedBall = b;
	}
	
	public Ball getCapturedBall() {
		return capturedBall;
	}

}
