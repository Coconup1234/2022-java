// 이 코드는 김윤서에 의해 짜졌고 if 문 같은 경우는 티스토리 반응자를 참고하여 제작됨
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TicTacToe extends JFrame {

	int cnt = 0;
	JPanel p, pp, mp, xp[][] = new JPanel[3][3];
	JLabel l, xl[][] = new JLabel[3][3];
	JButton b;
	Stack<JLabel> stack = new Stack<JLabel>();
	
	public TicTacToe() {
		// TODO Auto-generated constructor stub
		setTitle("");
		setSize(500, 500);
		setDefaultCloseOperation(2);
		setLocationRelativeTo(null);
		
		add(p = new JPanel(new BorderLayout()));
		
		p.add(l = new JLabel("X", JLabel.CENTER), BorderLayout.NORTH);
		p.add(mp = new JPanel(new GridLayout(3, 3)), BorderLayout.CENTER);
		p.add(pp = new JPanel(new FlowLayout(FlowLayout.CENTER)), BorderLayout.SOUTH);
		pp.add(b = new JButton("취소"));
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				var lb = new JLabel("", JLabel.CENTER);
				
				mp.add(xp[i][j] = new JPanel());
				xp[i][j].setBorder(new LineBorder(Color.black));
				xp[i][j].setLayout(new BorderLayout());
				xp[i][j].add(xl[i][j] = lb, BorderLayout.CENTER);
				xl[i][j].setFont(new Font("", 0, 100));
				
				xp[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if (lb.getText().isBlank()) {
							l.setText(cnt == 0 ? "X" : "O");
							lb.setText(cnt == 0 ? "X" : "O");
							stack.add(lb);
							cnt = (cnt + 1) % 2;
							check();
						}
					}
				});
			}
		}
		
		b.addActionListener(e -> {
			if(stack.isEmpty() == false)
				stack.pop().setText("");
		});
		
		check();
		setVisible(true);
	}
	
	void check() {
		for (int i = 0; i < 3; i++) {
			int l1 = xl[i][0].getText().hashCode();
			int l2 = xl[i][1].getText().hashCode(); 
			int l3 = xl[i][2].getText().hashCode();
			
			int ll1 = xl[0][i].getText().hashCode();
			int ll2 = xl[1][i].getText().hashCode(); 
			int ll3 = xl[2][i].getText().hashCode();
			
			int l11 = xl[0][0].getText().hashCode();
			int l22 = xl[1][1].getText().hashCode();
			int l33 = xl[2][2].getText().hashCode();
			
			int ll11 = xl[0][2].getText().hashCode();
			int ll22 = xl[2][0].getText().hashCode();
			
			if (l1 != 0 && l1 == l2 && l2 == l3) {
				win();
			} if (ll1 != 0 && ll1 == ll2 && ll2 == ll3) {
				win();
			} if (l11 != 0 && l11 == l22 && l22 == l33) {
				win();
			} if(l22 != 0 && ll11 == l22 && l22 == ll22) {
				win();
			}
		}
	}

	void win() {
		JOptionPane.showMessageDialog(null, l.getText() + "승리", "정보", JOptionPane.INFORMATION_MESSAGE);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				xl[i][j].setText("");
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new TicTacToe();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
