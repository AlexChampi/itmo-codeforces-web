package ru.itmo.wp.web.page;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private void action(Map<String, Object> view, HttpServletRequest request) {
        if (Objects.isNull(request.getSession().getAttribute("state"))) {
            newGame(request, view);
        } else {
            view.put("state", request.getSession().getAttribute("state"));
        }
    }

    public void newGame(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");
        if (Objects.nonNull(state) && state.isOver() || Objects.isNull(state)) {
            state = new State();
        }
        view.put("state", state);
        request.getSession().setAttribute("state", state);
        throw new RedirectException("/ticTacToe");
    }

    public void onMove(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");
        if (Objects.isNull(state)) {
            state = new State();
            view.put("state", state);
            request.getSession().setAttribute("state", state);
        }
        if (state.isOver()) {
            view.put("state", state);
            throw new RedirectException("/ticTacToe");
        }
        for (Map.Entry<String, String[]> parameter : request.getParameterMap().entrySet()) {
            if (parameter.getKey().startsWith("cell_") && parameter.getKey().length() == 7) {
                try {
                    int row = Integer.parseInt(String.valueOf(parameter.getKey().charAt(5)));
                    int col = Integer.parseInt(String.valueOf(parameter.getKey().charAt(6)));
                    state.doMove(row, col);
                } catch (NumberFormatException ignored) {
                    // no operations.
                }
            }
            view.put("state", state);
        }
        throw new RedirectException("/ticTacToe");
    }

    public static class State {
        private final int size;
        private final String[][] cells;
        private Phase phase;
        private boolean crossesMove;
        private int emptyCells;

        public State() {
            this.size = 10;
            this.cells = new String[size][size];
            this.phase = Phase.RUNNING;
            this.crossesMove = true;
            this.emptyCells = size * size;
        }

        public int getSize() {
            return size;
        }

        public String[][] getCells() {
            return cells;
        }

        public Phase getPhase() {
            return phase;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public void swapMove() {
            this.crossesMove = !crossesMove;
        }

        private void doMove(int row, int col) {
            if (isValidMove(row, col)) {
                cells[row][col] = isCrossesMove() ? "X" : "O";
                emptyCells--;
                checkPosition(row, col);
                swapMove();
            }
        }

        private boolean isValidMove(int row, int col) {
            return isBoard(row, col) && cells[row][col] == null;
        }

        private void checkPosition(int row, int col) {
            int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
            int[] counter = new int[4];
            Arrays.fill(counter, 1);
            for (int i = 1; i < 4; i++) {
                for (int j = 0; j < 8; j++) {
                    int r = row + i * directions[j][0];
                    int c = col + i * directions[j][1];
                    if (isBoard(r, c) &&
                            cells[row][col].equals(cells[r][c])) {
                        counter[j % 4]++;
                    }
                    if (counter[j % 4] == 3) {
                        phase = isCrossesMove() ? Phase.WON_X : Phase.WON_O;
                        return;
                    }
                }
            }
            if (emptyCells == 0) {
                phase = Phase.DRAW;
                return;
            }
        }

        private boolean isBoard(int row, int col) {
            return 0 <= row && row < size &&
                    0 <= col && col < size;
        }

        public boolean isOver() {
            return !phase.equals(Phase.RUNNING);
        }

        private enum Phase {
            RUNNING,
            WON_X,
            WON_O,
            DRAW
        }

    }
}
