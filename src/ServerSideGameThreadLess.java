class ServerSideGameThreadLess {

        Player player1;
        Player player2;
        Player currentPlayer;

        public ServerSideGameThreadLess(Player player1, Player player2){
            this.player1=player1;
            this.player2=player2;
            this.currentPlayer = player1;
            this.player1.setOpponent(player2);
            this.player2.setOpponent(player1);
        }

        /**
         * A board has nine squares. Each square is either unowned or
         * it is owned by a player. So we use a simple array of player
         * references. If null, the corresponding square is unowned,
         * otherwise the array cell stores a reference to the player that
         * owns it.
         */
        private Player[] board = {
                null, null, null,
                null, null, null,
                null, null, null};


        /**
         * Returns whether the current state of the board is such that one
         * of the players is a winner.
         */
        public boolean hasWinner() {
            return
                    (board[0] != null && board[0] == board[1] && board[0] == board[2])
                            ||(board[3] != null && board[3] == board[4] && board[3] == board[5])
                            ||(board[6] != null && board[6] == board[7] && board[6] == board[8])
                            ||(board[0] != null && board[0] == board[3] && board[0] == board[6])
                            ||(board[1] != null && board[1] == board[4] && board[1] == board[7])
                            ||(board[2] != null && board[2] == board[5] && board[2] == board[8])
                            ||(board[0] != null && board[0] == board[4] && board[0] == board[8])
                            ||(board[2] != null && board[2] == board[4] && board[2] == board[6]);
        }

        /**
         * Returns whether there are no more empty squares.
         */
        public boolean boardFilledUp() {
            for (int i = 0; i < board.length; i++) {
                if (board[i] == null) {
                    return false;
                }
            }
            return true;
        }

    /*
    The game loop, receiving the squares clicked by clients
    Checking if a client has won, lost or if there is a tie.
     */

        public void doGame(){

            player1.send("WELCOME " + player1.mark);
            player1.send("MESSAGE Waiting for opponent to connect");

            player2.send("WELCOME " + player2.mark);
            player2.send("MESSAGE All players connected");

            player1.send("MESSAGE Your move");

            String command;
            currentPlayer = player1;

            while (true) {
                command = currentPlayer.receive();  //ta emot från klient

                if (command.startsWith("MOVE")) {
                    int location = Integer.parseInt(command.substring(5));

                    if (board[location] == null) {  //clicked empty square = legal move
                        board[location] = currentPlayer;

                        currentPlayer.send("VALID_MOVE");
                        currentPlayer.getOpponent().send("OPPONENT_MOVED " + location);

                        if (hasWinner()){
                            currentPlayer.send("VICTORY");
                            currentPlayer.getOpponent().send("DEFEAT");
                        }
                        else if (boardFilledUp()){
                            currentPlayer.send("TIE");
                            currentPlayer.getOpponent().send("TIE");
                        }
                        else{
                            currentPlayer.send("");
                            currentPlayer.getOpponent().send("");
                        }
                        currentPlayer = currentPlayer.getOpponent();  //BYTER SPELARE

                    } else {
                        currentPlayer.send("MESSAGE ?");
                    }

                } else if (command.startsWith("QUIT")) {
                    return;
                }
            }
        }


}
