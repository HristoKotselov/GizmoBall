import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFrame;

public class FileLoaderPrototype {
	private Board b;

	public FileLoaderPrototype() {
		JFrame f = new JFrame("Gizmoball - File Loader Prototype");
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b = new Board();
		f.add(b);

		f.setVisible(true);
		loadFile();
	}

	private void loadFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("example.txt")));

			while (br.ready()) {
				String line = br.readLine();

				StringTokenizer st = new StringTokenizer(line);

				if (st.hasMoreTokens()) {

					String name;
					int x;
					int y;
					Gizmo g;

					switch (st.nextToken()) {
						case "Triangle":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());
							System.out.println("Adding triangle bumper named \"" + name + "\" at (" + x + ", " + y + ")");

							g = new TriangleBumper(name, x, y);
							b.addGizmo(name, g);
							break;

						case "Square":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());
							System.out.println("Adding square bumper named \"" + name + "\" at (" + x + ", " + y + ")");

							g = new SquareBumper(name, x, y);
							b.addGizmo(name, g);
							break;
							
						case "Circle":
							name = st.nextToken();
							x = Integer.parseInt(st.nextToken());
							y = Integer.parseInt(st.nextToken());
							System.out.println("Adding circle bumper named \"" + name + "\" at (" + x + ", " + y + ")");

							g = new CircleBumper(name, x, y);
							b.addGizmo(name, g);
							break;

						case "Rotate":
							b.getGizmo(st.nextToken()).rotate();
							break;

						default:
							System.out.println("LINE NOT IMPLEMENTED - " + line);
							break;
					}
				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileLoaderPrototype();
	}
}