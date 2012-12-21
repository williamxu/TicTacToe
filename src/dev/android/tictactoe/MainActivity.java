package dev.android.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private Button[][] ticTacToeButtons;

    private TextView gameStateTextView;

    private TicTacToeGame game;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	game = new TicTacToeGame(this);
	ticTacToeButtons = new Button[TicTacToeGame.NUM_ROWS][TicTacToeGame.NUM_COLUMNS];
	ticTacToeButtons[0][0] = (Button) findViewById(R.id.button00);
	ticTacToeButtons[0][1] = (Button) findViewById(R.id.button01);
	ticTacToeButtons[0][2] = (Button) findViewById(R.id.button02);
	ticTacToeButtons[1][0] = (Button) findViewById(R.id.button10);
	ticTacToeButtons[1][1] = (Button) findViewById(R.id.button11);
	ticTacToeButtons[1][2] = (Button) findViewById(R.id.button12);
	ticTacToeButtons[2][0] = (Button) findViewById(R.id.button20);
	ticTacToeButtons[2][1] = (Button) findViewById(R.id.button21);
	ticTacToeButtons[2][2] = (Button) findViewById(R.id.button22);
	gameStateTextView = (TextView) findViewById(R.id.textView2);
	for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
	    for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
		ticTacToeButtons[row][col].setOnClickListener(this);
	    }
	}
	((Button) findViewById(R.id.new_game_button)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
    }

    public void onClick(View v) {
	Resources r = getResources();
	String gameState = gameStateTextView.getText().toString();
	if (gameState == r.getString(R.string.x_win)
		|| gameState == r.getString(R.string.o_win)
		|| gameState == r.getString(R.string.tie_game)) {
	    if (v.getId() == R.id.new_game_button) {
		game.resetGame();
		for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
		    for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
			ticTacToeButtons[row][col].setEnabled(true);
		    }
		}
	    } else
		return;
	}
	for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
	    for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
		if (v.getId() == ticTacToeButtons[row][col].getId()) {
		    ticTacToeButtons[row][col].setEnabled(false);
		    game.pressedButtonAtLocation(row, col);
		}
		ticTacToeButtons[row][col].setText(game
			.stringForButtonAtLocation(row, col));
	    }
	}
	gameStateTextView.setText(game.stringForGameState());
    }
}
