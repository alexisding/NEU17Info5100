package midterm;

import java.util.ArrayList;

/**
 * Created by alexis on 10/25/17.
 */
public class Cell {
	int x,y;

	Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}

	static class Solution {

		public ArrayList<Cell> findPath(int[][] maze) {

			ArrayList<Cell> result = new ArrayList<>();

			// check if the maze is null
			if (maze == null) {
				return result;
			}

			int N = maze.length;
			int[][] helperMaze = new int[N][N]; // create a helper maze
			findPath(maze, 0, 0, helperMaze);

			System.out.println("Print path:");
			for (int i = 0; i < N; i ++) {
				for (int j = 0; j < N; j++) {
					System.out.print(" " + helperMaze[i][j] + " ");
					if (helperMaze[i][j] == 1) {
						Cell cell = new Cell(i, j);
						result.add(cell);
					}
				}
				System.out.println();
			}
			System.out.println("output: " + result);
			return result;
		}

		// move down and forward, save available path to the helper maze
		public boolean findPath(int[][] maze, int x, int y, int[][] helperMaze) {

			int N = maze.length;

			if (x == N - 1 && y == N - 1 && maze[x][y] == 1) {
				helperMaze[x][y] = 1;
				return true;
			}

			if (isAvail(maze, x, y)) {
				// set the available Cell to 1
				helperMaze[x][y] = 1;
				// move down
				if(findPath(maze, x + 1, y, helperMaze)) {
					return true;
				}
				// move forward
				if(findPath(maze, x, y + 1, helperMaze)) {
					return true;
				}
				// set the Cell to 0
				helperMaze[x][y] = 0;
			}
			return false;
		}

		// check if the cell is available for rat to go
		private boolean isAvail(int[][] maze, int x, int y) {
			int N = maze.length;
			return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
		}
	}

	public static void main(String[] args) {
		int[][] maze1 = {
				{1, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 1, 0, 0},
				{1, 1, 1, 1}
		};

		int[][] maze2 = {
				{1, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 0, 0, 0},
				{1, 1, 1, 1}
		};

		int[][] maze3 = {
				{1, 0},
				{1, 1}
		};

		int[][] maze4 = {
				{1, 0},
				{0, 1}
		};

		int[][] maze5 = {
				{0}
		};

		int[][] maze6 = {
				{1}
		};

		int[][] maze7 = {};

		int[][] maze8 = null;

		Solution solution = new Solution();
		solution.findPath(maze1);
		solution.findPath(maze2);
		solution.findPath(maze3);
		solution.findPath(maze4);
		solution.findPath(maze5);
		solution.findPath(maze6);
		solution.findPath(maze7);
		solution.findPath(maze8);
	}
}
