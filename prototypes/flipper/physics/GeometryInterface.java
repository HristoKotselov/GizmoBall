package flipper.physics;import java.lang.Double; // import statement added to mollify javadocimport java.util.Iterator;import flipper.physics.Geometry.DoublePair;import flipper.physics.Geometry.VectPair;/** * This is the interface that the singleton Geometry dispatches to. * Callers will probably wish to use the singleton Geometry in most * cases. * * @see flipper.physics.Geometry **/public interface GeometryInterface {  /**   * Specified by Geometry.quadraticSolution   *   * @see flipper.physics.Geometry#quadraticSolution   **/  public DoublePair quadraticSolution(double a, double b, double c);  /**   * Specified by Geometry.minQuadraticSolution   *   * @see flipper.physics.Geometry#minQuadraticSolution   **/  public double minQuadraticSolution(double a,				     double b,				     double c);  /**   * Specified by Geometry.perpendicularPoint   *   * @see flipper.physics.Geometry#perpendicularPoint   **/  public Vect perpendicularPoint(LineSegment line,				 Vect point);  /**   * Specified by Geometry.perpendicularPointWholeLine   *   * @see flipper.physics.Geometry#perpendicularPointWholeLine   **/  public Vect perpendicularPointWholeLine(LineSegment line,					  Vect point);  /**   * Specified by Geometry.applyReflectionCoeff   *   * @see flipper.physics.Geometry#applyReflectionCoeff   **/  public Vect applyReflectionCoeff(Vect incidentVect,				   Vect reflectedVect,				   double rCoeff);  /**   * Specified by Geometry.timeUntilWallCollision   *   * @see flipper.physics.Geometry#timeUntilWallCollision   **/  public double timeUntilWallCollision(LineSegment line,				       Circle ball,				       Vect velocity);  /**   * Specified by Geometry.reflectWall   *   * @see flipper.physics.Geometry#reflectWall   **/  public Vect reflectWall(LineSegment line,			  Vect velocity,			  double reflectionCoeff);  /**   * Specified by Geometry.reflectWall   *   * @see flipper.physics.Geometry#reflectWall   **/  public Vect reflectWall(LineSegment line,			  Vect velocity);  /****************************************************************************   *   * METHODS FOR CIRCLES   *   ***************************************************************************/  /**   * Specified by Geometry.distanceSquared   *   * @see flipper.physics.Geometry#distanceSquared   **/  public double distanceSquared(Vect v1, Vect v2);  /**   * Specified by Geometry.distanceSquared   *   * @see flipper.physics.Geometry#distanceSquared   **/   public double distanceSquared(double x1, double y1,				 double x2, double y2);  /**   * Specified by Geometry.timeUntilCircleCollision   *   * @see flipper.physics.Geometry#timeUntilCircleCollision   **/  public double timeUntilCircleCollision(Circle circle,					 Circle ball,					 Vect velocity);  /**   * Specified by Geometry.reflectCircle   *   * @see flipper.physics.Geometry#reflectCircle   **/  public Vect reflectCircle(Vect circle,			    Vect ball,			    Vect velocity, 			    double reflectionCoeff);  /**   * Specified by Geometry.reflectCircle   *   * @see flipper.physics.Geometry#reflectCircle   **/  public Vect reflectCircle(Vect circle,			    Vect ball,			    Vect velocity);  /****************************************************************************   *   * METHODS FOR ROTATING LINE SEGMENTS AND CIRCLES   *   ***************************************************************************/  /**   * Specified by Geometry.rotateAround   *   * @see flipper.physics.Geometry#rotateAround   **/  public Vect rotateAround(Vect point, Vect cor, Angle a);  /**   * Specified by Geometry.rotateAround   *   * @see flipper.physics.Geometry#rotateAround   **/  public LineSegment rotateAround(LineSegment line, Vect cor, Angle a);  /**   * Specified by Geometry.rotateAround   *   * @see flipper.physics.Geometry#rotateAround   **/  public Circle rotateAround(Circle circle, Vect cor, Angle a);  /**   * Specified by Geometry.timeUntilCircleCollision   *   * @see flipper.physics.Geometry#timeUntilCircleCollision   **/  public DoublePair timeUntilCircleCollision(Circle circle,					     Vect point,					     Vect velocity);  /**   * Specified by Geometry.timeUntilRotatingWallCollision   *   * @see flipper.physics.Geometry#timeUntilRotatingWallCollision   **/  public double timeUntilRotatingWallCollision(LineSegment line,					       Vect center,					       double angularVelocity,					       Circle ball,					       Vect velocity);  /**   * Specified by Geometry.reflectRotatingWall   *   * @see flipper.physics.Geometry#reflectRotatingWall   **/  public Vect reflectRotatingWall(LineSegment line,				  Vect center,				  double angularVelocity,				  Circle ball,				  Vect velocity);  /**   * Specified by Geometry.reflectRotatingWall   *   * @see flipper.physics.Geometry#reflectRotatingWall   **/  public Vect reflectRotatingWall(LineSegment line,				  Vect center,				  double angularVelocity,				  Circle ball,				  Vect velocity,				  double reflectionCoeff);  /**   * Specified by Geometry.timeUntilRotatingCircleCollision   *   * @see flipper.physics.Geometry#timeUntilRotatingCircleCollision   **/  public double timeUntilRotatingCircleCollision(Circle circle,						 Vect center,						 double angularVelocity,						 Circle ball,						 Vect velocity);  /**   * Specified by Geometry.reflectRotatingCircle   *   * @see flipper.physics.Geometry#reflectRotatingCircle   **/  public Vect reflectRotatingCircle(Circle circle,				    Vect center,				    double angularVelocity,				    Circle ball,				    Vect velocity);  /**   * Specified by Geometry.reflectRotatingCircle   *   * @see flipper.physics.Geometry#reflectRotatingCircle   **/  public Vect reflectRotatingCircle(Circle circle,				    Vect center,				    double angularVelocity,				    Circle ball,				    Vect velocity,				    double reflectionCoeff);  /****************************************************************************   *   * METHODS FOR MULTI-BALL SIMULATIONS   *   ***************************************************************************/  /**   * Specified by Geometry.timeUntilBallBallCollision   *   * @see flipper.physics.Geometry#timeUntilBallBallCollision   **/  public double timeUntilBallBallCollision(Circle ball1,					   Vect   vel1,					   Circle ball2,					   Vect   vel2);  /**   * Specified by Geometry.reflectBalls   *   * @see flipper.physics.Geometry#reflectBalls   **/  public VectPair reflectBalls(Vect center1,			       double mass1,			       Vect velocity1,			       Vect center2,			       double mass2,			       Vect velocity2);}