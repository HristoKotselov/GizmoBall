package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * NOTE: This is suppose to be a Static Class, so please do not try to
 * instantiate it
 *
 */
public final class SaveDataEngine {

	private SaveDataEngine() {
	} // prevent instantiation

	public static void loadFile(String filepath, MainEngine model) {
		System.out.println("loading board from \"" + filepath + "\"");

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));

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
							System.out.println("Adding left flipper named \"" + name + "\" at (" + x + ", " + y + ")");

							g = new Flipper(name, x, y, Color.ORANGE, true);
							model.addGizmo(g);
							break;

						case "RightFlipper":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());
							System.out.println("Adding right flipper named \"" + name + "\" at (" + x + ", " + y + ")");

							g = new Flipper(name, x, y, Color.ORANGE, false);
							model.addGizmo(g);
							break;

						case "Rotate":
							name = st.nextToken();
							System.out.println("Rotating \"" + name + "\" by 90 degrees");
							g = model.getGizmo(name);
							model.rotateGizmo(g, 90);
							break;

						case "Absorber":
							// name = st.nextToken();
							// x = Integer.parseInt(st.nextToken());
							// y = Integer.parseInt(st.nextToken());
							// int w = Integer.parseInt(st.nextToken()) - x;
							// int h = Integer.parseInt(st.nextToken()) - y;
							// System.out.println("Adding absorber named \"" +
							// name
							// + "\" at (" + x + ", " + y + ") with w = " + w +
							// ", h
							// = " + h);
							//
							// g = new Absorber(name, x, y, w, h);
							// b.addGizmo(name, g);
							// break;

						case "Move":
							// name = st.nextToken();
							// x = Integer.parseInt(st.nextToken());
							// y = Integer.parseInt(st.nextToken());
							// System.out.println("Moving \"" + name + "\" to ("
							// + x
							// + ", " + y + ")");
							//
							// g = b.getGizmo(name);
							// g.setX(x);
							// g.setY(y);
							// break;

						case "Delete":
							// name = st.nextToken();
							// System.out.println("Deleting \"" + name + "\"");
							//
							// b.removeGizmo(name);
							// break;

						case "Ball":
							// name = st.nextToken();
							// double xpos = Double.parseDouble(st.nextToken());
							// double ypos = Double.parseDouble(st.nextToken());
							// double xvel = Double.parseDouble(st.nextToken());
							// double yvel = Double.parseDouble(st.nextToken());
							// System.out.println("Adding ball named \"" + name
							// +
							// "\" at (" + xpos + ", " + ypos + ") with xvel = "
							// +
							// xvel + ", yvel = " + yvel);
							//
							// Ball ball = new Ball(xpos, ypos, xvel, yvel);
							// b.addBall(name, ball);
							// break;

						case "Connect":
						case "KeyConnect":
						case "Gravity":
						case "Friction":
							System.err.println("Functionality for \"" + s + "\" not implemented");
							break;

						default:
							System.err.println("Invalid command");
							break;
					}
				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveFile(String filePath, MainEngine model) {
		// TODO Auto-generated method stub

	}

}