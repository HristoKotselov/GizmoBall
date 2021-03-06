package model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import model.gizmos.Absorber;
import model.gizmos.Ball;
import model.gizmos.CircularBumper;
import model.gizmos.Flipper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangularBumper;
import physics.Vect;



/**
 * NOTE: This is suppose to be a Static Class, so please do not try to instantiate it
 *
 */
public final class SaveDataEngine {

	private SaveDataEngine() {
	} // prevent instantiation

	public static boolean loadFile(String filepath, MainEngine model) {
		System.out.println("loading board from \"" + filepath + "\"");

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

			boolean gravitySet = false;
			boolean frictionSet = false;

			while (br.ready()) {
				String line = br.readLine();

				StringTokenizer st = new StringTokenizer(line);

				if (st.hasMoreTokens()) {
					String name;
					int x;
					int y;
					AGizmoComponent g;

					String s = st.nextToken();

					switch (s) {
						case "Triangle":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());

							g = new TriangularBumper(name, x, y, Color.BLUE);
							model.addGizmo(g);
							break;

						case "Square":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());

							g = new SquareBumper(name, x, y, Color.RED);
							model.addGizmo(g);
							break;

						case "Circle":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());

							g = new CircularBumper(name, x, y, Color.GREEN);
							model.addGizmo(g);
							break;

						case "LeftFlipper":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());

							g = new Flipper(name, x, y, Color.ORANGE, Flipper.LEFT);
							model.addGizmo(g);
							break;

						case "RightFlipper":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());

							g = new Flipper(name, x, y, Color.ORANGE, Flipper.RIGHT);
							model.addGizmo(g);
							break;

						case "Rotate":
							name = st.nextToken();
							g = model.getGizmo(name);
							model.rotateGizmo(g, 90);
							break;

						case "Absorber":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());
							int w = Integer.parseInt(st.nextToken()) - x;
							int h = Integer.parseInt(st.nextToken()) - y;

							g = new Absorber(name, x, y, w, h, Color.MAGENTA);
							model.addGizmo(g);
							break;

						case "Move":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());

							g = model.getGizmo(name);
							if (g instanceof AStationaryGizmo) {
								model.moveGizmoByL(g, x, y);
							}
							if (g instanceof AMovingGizmo) {
								model.moveGizmoByPixels((AMovingGizmo) g, x, y);
							}

							break;

						case "Delete":
							name = st.nextToken();
							g = model.getGizmo(name);
							model.removeGizmo(g);
							break;

						case "Ball":
							name = st.nextToken();

							int xpos = (int) (Double.parseDouble(st.nextToken()) * IMainEngine.L);
							int ypos = (int) (Double.parseDouble(st.nextToken()) * IMainEngine.L);

							double xvel = Double.parseDouble(st.nextToken()) * IMainEngine.L;
							double yvel = Double.parseDouble(st.nextToken()) * IMainEngine.L;

							Vect v = new Vect(xvel, yvel);

							g = new Ball(name, Color.BLUE, xpos, ypos, v.angle(), v.length());
							model.addGizmo(g);
							break;

						case "KeyConnect":
							st.nextToken();
							int key = Integer.parseInt(st.nextToken());
							String type = st.nextToken();
							name = st.nextToken();

							g = model.getGizmo(name);

							if (type.equals("down")) {
								model.bindKey(g, key, KeyEvent.KEY_PRESSED);
							} else {
								model.bindKey(g, key, KeyEvent.KEY_RELEASED);
							}
							break;

						case "Connect":
							name = st.nextToken();
							AGizmoComponent g1 = model.getGizmo(name);

							name = st.nextToken();
							AGizmoComponent g2 = model.getGizmo(name);

							model.addConnection(g1, g2);
							break;

						case "Gravity":
							double gravity = Double.parseDouble(st.nextToken()) * IMainEngine.L;
							model.getPhysicsConfig().setGravity(gravity);
							gravitySet = true;
							break;

						case "Friction":
							double mu1 = Double.parseDouble(st.nextToken());
							double mu2 = Double.parseDouble(st.nextToken()) / IMainEngine.L;

							model.getPhysicsConfig().setFrictionCoef1(mu1);
							model.getPhysicsConfig().setFrictionCoef2(mu2);

							frictionSet = true;
							break;

						default:
							System.err.println("Invalid command");
							break;
					}
				}
			}

			if (!gravitySet) {
				model.getPhysicsConfig().setGravity(PhysicsConfig.DEFAULT_GRAVITY);
			}

			if (!frictionSet) {
				model.getPhysicsConfig().setFrictionCoef1(PhysicsConfig.DEFAULT_MU1);
				model.getPhysicsConfig().setFrictionCoef2(PhysicsConfig.DEFAULT_MU2);
			}

			br.close();
		} catch (FileNotFoundException ex) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static boolean saveFile(String filePath, MainEngine model) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));

			Collection<AGizmoComponent> gizmos = model.getAllGizmos();

			for (AGizmoComponent g : gizmos) {
				bw.write(g.toString() + "\n");
			}

			bw.write(model.getBall().toString() + "\n");

			Connections c = model.getConnections();

			for (Map.Entry<Integer, Set<AGizmoComponent>> entry : c.getKeyPressBindings().entrySet()) {
				for (AGizmoComponent g : entry.getValue()) {
					bw.write("KeyConnect key " + entry.getKey() + " down " + g.getGizmoID() + "\n");
				}
			}

			for (Map.Entry<Integer, Set<AGizmoComponent>> entry : c.getKeyReleaseBindings().entrySet()) {
				for (AGizmoComponent g : entry.getValue()) {
					bw.write("KeyConnect key " + entry.getKey() + " up " + g.getGizmoID() + "\n");
				}
			}

			for (Map.Entry<AGizmoComponent, Set<AGizmoComponent>> entry : c.getConnections().entrySet()) {
				for (AGizmoComponent g : entry.getValue()) {
					bw.write("Connect " + entry.getKey().getGizmoID() + " " + g.getGizmoID() + "\n");
				}
			}

			bw.write(model.getPhysicsConfig().toString() + "\n");

			bw.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}