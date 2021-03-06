package absorber;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber extends AGizmoComponent implements ILineSegmentCollider{
	/** The amount of width this absorber takes up in the board (in pixel). Absorber will always at least take up 1L*1L worth of space    **/
	private int width;
	/** The amount of height this absorber takes up in the board (in pixel). Absorber will always at least take up 1L*1L worth of space    **/
	private int height;
	/** A set of Line Segments around the edge of the absorber, which will act as the collision detector with a ball **/
	private Set<LineSegment> ls;
	/** The currently captured Ball within the Absorber. If there is no ball within the absorber, then this object becomes null. */
	private Ball capturedBall;
	
	
	
	public Absorber(String name, int grid_tile_x, int grid_tile_y, int widthInL, int heightInL, Color color) {
		super(name, grid_tile_x * Model.L, grid_tile_y * Model.L, color);
		
		setWidth(widthInL * Model.L);
		setHeight(heightInL * Model.L);
		capturedBall = null;
		
		ls = new HashSet<LineSegment>();
		setupLineSeg();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void triggerAction() {
		if(capturedBall != null){		// no ball in absorber = nothing happens
			// In this physics package, ANGLE.ZERO is RHS of x-axis; degree increasing clock-wise. 50L is the length, thus it is converted to pixels here
			capturedBall.setVelo(new Vect(Angle.DEG_270, 50 * Model.L));
		}

	}
	
	@Override
	protected void setupDrawingShape() {
		// TODO Auto-generated method stub

	}
	
	/* Absorber's collision detector methods */
	@Override
	protected void setupCircles() {
		int lCorner_X = getX();
		int rCorner_X = getX() + getWidth();
		int tCorner_Y = getY();
		int bCorner_Y = getY() + getHeight();
		
		Circle tlCorner = new Circle(lCorner_X, tCorner_Y, 0.0);
		Circle trCorner = new Circle(rCorner_X, tCorner_Y, 0.0);
		Circle brCorner = new Circle(rCorner_X, bCorner_Y, 0.0);
		Circle blCorner = new Circle(lCorner_X, bCorner_Y, 0.0);
		
		circleSet.add(tlCorner);
		circleSet.add(trCorner);
		circleSet.add(brCorner);
		circleSet.add(blCorner);
	}
	
	/**
	 * The setup of the Line Segment collection. Gizmo component that rely on Line Segments for collision detection should
	 * set up their Line Segment objects here.
	 * @modify this
	 * @effect Fill the collection which hold all the Line Segments in this class with appropriate objects
	 */
	private void setupLineSeg() {
		int lCorner_X = getX();
		int rCorner_X = getX() + getWidth();
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
