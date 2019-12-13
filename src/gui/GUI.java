package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import algorithms.Genetic;
import algorithms.HillClimbing;
import algorithms.IAlgorithm;
import algorithms.KBeam;
import state.State;

public class GUI {

	static JFrame mainFrame;
	static int queensCount = 0;
	static boolean[][] b = new boolean[8][8];

	public static void main(String[] args) {
		mainFrame = new JFrame();
		mainFrame.setSize(1200, 830);
		mainFrame.setLayout(null);
		getStartState();
		addOptions();
		mainFrame.setVisible(true);
	}

	public static void addOptions() {
		JLabel title = new JLabel();
		title.setText("Choose an algorithm");
		title.setBounds(800, 10, 400, 100);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("SansSerif", 1, 28));
		title.setVerticalAlignment(SwingConstants.CENTER);
		JButton hc = algorithmBtn(1, "Hill Climbing");
		hc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (queensCount != 8) {
					JOptionPane.showMessageDialog(null, "Can't start hill climbing with " + queensCount + " queens");
					System.out.println("Can't start hill climbing with " + queensCount + " queens");
				} else {
					HillClimbing hcAlgo = new HillClimbing(fromMatrix(b));
					board(hcAlgo.getFinalState());
					hcAlgo.getFinalState().printBoard();
					addPerformanceDetails(hcAlgo);
					ArrayList<State> path = hcAlgo.getPath();
					System.out.println("Path: ");
					for (State s: path) {
						s.printBoard();
						System.out.println();
					}

				}
			}
		});

		JButton kb = algorithmBtn(2, "K-Beam");
		JTextField k = new JTextField();
		k.setText("K");
		k.setBounds(800, 310, 200, 80);
		mainFrame.add(k);
		JTextField iters = new JTextField();
		iters.setText("iterations");
		iters.setBounds(1000, 310, 200, 80);
		mainFrame.add(iters);
		kb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String kStr = k.getText();
				String itersStr = iters.getText();
				try {
					int kInt = Integer.parseInt(kStr);
					int itersInt = Integer.parseInt(itersStr);
					KBeam kbeam = new KBeam(itersInt, kInt);
					board(kbeam.getFinalState());
					addPerformanceDetails(kbeam);
				} catch (Exception exc) {

				}
			}
		});

		JButton ga = algorithmBtn(4, "Genetic Algorithm");
		ga.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Genetic g = new Genetic();
				board(g.getFinalState());
				addPerformanceDetails(g);
			}
		});
		mainFrame.add(hc);
		mainFrame.add(kb);
		mainFrame.add(ga);
		mainFrame.add(title);
	}

	public static void addPerformanceDetails(IAlgorithm algo) {
		JLabel runtime = new JLabel();
		runtime.setBounds(900, 20, 400, 50);
		JLabel expandedNodes = new JLabel();
		expandedNodes.setBounds(900, 100, 400, 50);

		JLabel cost = new JLabel();
		cost.setBounds(900, 180, 400, 50);

		runtime.setText("Running time = " + algo.getRunTime() + "ms");
		expandedNodes.setText("Expanded nodes = " + algo.getExpandedNodes());
		cost.setText("Cost = " + algo.getCost());
		mainFrame.add(runtime);
		mainFrame.add(expandedNodes);
		mainFrame.add(cost);

	}

	public static JButton algorithmBtn(int index, String title) {
		JButton btn = new JButton();
		btn.setText(title);
		btn.setBounds(800, 100 * index, 400, 90);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setBackground(new Color(130, 204, 221));
		btn.setOpaque(true);
		btn.setBorderPainted(false);
		btn.setFocusable(false);
		btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
            	btn.setBackground(new Color(183, 21, 64));
            }

            public void mouseExited(MouseEvent me) {
        		btn.setBackground(new Color(130, 204, 221));
            }
		});
		return btn;
	}

	public static JFrame board(State s) {
		mainFrame = new JFrame();
		mainFrame.setSize(1200, 830);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		boolean color = true;
		for (int i = 0; i < 8; i++) {
			color = !color;
			for (int j = 0; j < 8; j++) {
				JLabel lbl = new JLabel();
				if (color) {
					lbl.setBackground(new Color(223, 228, 234));
				} else {
					lbl.setBackground(new Color(241, 242, 246));
				}
				if (s.getBoard()[i][j]) {
					lbl.setIcon(new ImageIcon("princess.png"));
				}
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
				lbl.setVerticalAlignment(SwingConstants.CENTER);
				lbl.setOpaque(true);

				lbl.setBounds(j * 100, i * 100, 100, 100);
				mainFrame.add(lbl);
				color = !color;
			}
		}
		return mainFrame;
	}

	public static void getStartState() {
		boolean color = true;
		int[] index = new int[2];
		int i = 0;
		for (i = 0; i < 8; i++) {
			color = !color;
			for (int j = 0; j < 8; j++) {
				index[0] = i;
				index[1] = j;
				JLabel lbl = new JLabel();
				if (color) {
					lbl.setBackground(new Color(223, 228, 234));
				} else {
					lbl.setBackground(new Color(241, 242, 246));
				}
				lbl.setHorizontalAlignment(SwingConstants.CENTER);
				lbl.setVerticalAlignment(SwingConstants.CENTER);
				lbl.setOpaque(true);
				lbl.setBounds(j * 100, i * 100, 100, 100);
				mainFrame.add(lbl);
				color = !color;
				lbl.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.out.println("Clicked!");
						int x = lbl.getBounds().x / 100;
						int y = lbl.getBounds().y / 100;
						if (!b[y][x]) {
							lbl.setIcon(new ImageIcon("princess.png"));
							queensCount++;
						} else {
							lbl.setIcon(null);
							lbl.revalidate();
							queensCount--;
						}
						b[y][x] = !b[y][x];
						System.out.println(lbl.getBounds());
						System.out.println(queensCount);

					}
				});
			}
		}
	}

	public static State fromMatrix(boolean[][] matrix) {
		int[] rows = new int[8], cols = new int[8];
		int queens = 0;
		for (int i = 0; i < matrix.length && queens < 8; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j]) {
					rows[queens] = i;
					cols[queens] = j;
					queens++;
				}
			}
		}
		return new State(rows, cols, 0);
	}
}
