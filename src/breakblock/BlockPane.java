package breakblock;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BlockPane extends Pane implements Observer {

	ArrayList<Block> inv = new ArrayList<Block>();
	
	Timeline timeline;
	int flg = 0;
	Label lb;
	//Button b;
	int LR = 5; //左右の当たり判定幅
	int LRy = 2; //左右の当たり判定幅の上下差分
	Button startBack;
	Button nextStage;
	Label ll;
	static int score;
	Label sc; //スコア用ラベル
	
	//デバッグ用
	Label lb1; //ボールのXY
	Label lb2; //ボールの速度
	
	//コンストラクタ
	BlockPane(Label lb/*,Button b:*/,Label lb1,Label lb2,Label ll,Button startBack,Button nextStage,Label sc){
		this.lb = lb;
		this.lb1 = lb1;
		this.lb2 = lb2;
		this.startBack = startBack;
		this.nextStage = nextStage;
		this.ll = ll;
		this.sc = sc;
		
		//デバッグ用ラベルを非表示に
		this.lb1.setVisible(false);
		this.lb2.setVisible(false);
		
		//this.b = b;
		//初期値セット
		setLayoutX(Screen.BlockPane_X);
		setLayoutY(Screen.BlockPane_Y);
		
		//必要なら背景画像
		//setBackground()
		
		//ブロック生成
		int count = 0;
		int[][] hai = new int[][] {
			//stage1
			{0,0,0,1,1,1,0,0,0,0,1,1,0,0,0},
			{0,0,0,0,1,0,0,0,0,1,0,0,1,0,0},
			{0,0,0,0,1,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0,0,1,1,0,0,0},
			{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,1,0,0,0,0,1,0,0,1,0,0},
			{0,0,0,1,1,1,0,0,0,0,1,1,0,0,0}
			
			//stage2
//			{1,1,1,0,0,1,0,0,0,1,0,1,1,1,0},
//			{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
//			{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
//			{1,1,1,0,0,1,0,0,0,1,0,1,1,1,0},
//			{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
//			{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
//			{1,1,1,0,0,0,1,1,1,0,0,1,1,1,0}
			
			//stage3
//			{0,1,0,0,0,0,0,1,0,0,0,0,0,1,0},
//			{1,1,1,0,0,0,1,0,1,0,0,0,1,1,1},
//			{0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
//			{0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
//			{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
//			{0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
//			{0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
//			{0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
//			{0,0,1,0,0,0,1,1,1,0,0,0,1,0,0},
//			{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
//			{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
			
			//stage3_easy
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,1,0,1,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
//			{0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			
		};
		
		for(int i=0;i<hai.length;i++) {
			for(int j=0;j<hai[i].length;j++) {
				if(hai[i][j] != 0) {
					inv.add(count,new Block(j*Screen.Block_W,i*Screen.Block_H));
					getChildren().add(inv.get(count));
					count++;
				}
			}
		}
		
	/*	int count = 0;
		int[][] n = new int[][] {
			{10101},
			{01010},
			{10101},
		};
		for(int i=0;i<n.length;i++) {
			for(int j=0;j<n[i].length;j++) {
				if(n[i][j] != 0) {
					inv.add(count,new Block(j*Screen.Block_W,i*Screen.Block_H));
					getChildren().add(inv.get(count));
					count++;
				}
			}
		}*/
		
	/*	int count = 0;
		for(int i = 0; i < 7;i++) {
			if(i==1||i==3||i==5) {
				continue;
			}
			for(int j = 0;j < 5;j++) {
				if(j==1||j==3||j==5) {
					continue;
				}
				//inv(インベーダ単体)のデータを作成していく
				//Paneに、invを載せるgetChildren().add();
				inv.add(count,new Block(j*Screen.Block_W,i*Screen.Block_H));
				getChildren().add(inv.get(count));
				count++;
			}
		}*/
		
		/*//タイムラインの準備・実行
		timeline = new Timeline(new KeyFrame(Duration.millis(300),event->{run();}));
		timeline.setCycleCount(Timeline.INDEFINITE);//永久に
		timeline.play();*/
	}

	//スレッドの実行
	/*public void run() {
		int x_add=20;
		int y_add=20;
		
		//向きに応じてsetLayoutX()など
		if(getLayoutX()+Screen.BlockPane_W > Screen.Right && flg == 0) {
			setLayoutY(getLayoutY()+100);
			flg = 1;
			return;
		}
		else if(getLayoutX() < Screen.Left && flg == 1){
			setLayoutY(getLayoutY()+100);
			flg = 0;
			return;
		}
		if(flg==0)
			setLayoutX(getLayoutX()+50);
		else {
			setLayoutX(getLayoutX()-50);
		}
		if(getLayoutY()>280&&invaderLifeCheck()!=false) {
			lb.setVisible(true);
			//b.setVisible(true);
		}
	}*/
	
	//ミサイルからの通知
	public void update(Ball s) {
		Ball b = s;
		int bx = (int)(s).getX();
		int by = (int)(s).getY();
		int bw = Screen.Ball_W;
		int bh = Screen.Ball_H;
		
		//デバッグ
		lb1.setText("ボールのxy:(" + String.valueOf(b.getX()) + "," + String.valueOf(b.getY()) + ")");
		lb2.setText("ボールの速度:(" + String.valueOf(b.getVx()) + "," + String.valueOf(b.getVy()) + ")");
		
		//当たったか判定
		for(Block i : inv) {
			System.out.println("x="+i.getX());
			System.out.println("y="+i.getY());
			//if(i.getX()<=i.getX()+Screen.Block_W && i.getY() == i.getY()+Screen.Block_H) {
			System.out.printf("【inv】(%d,%d)(%d,%d)\n",bx,by,(int)(i.getX()+getLayoutX()),(int)(i.getY()+getLayoutY()));
			
			//左に当たったら (LR=左右の当たり判定幅、LRy=左右の当たり判定幅の上下差分)
			if(Screen.hitCheck2(bx,by,bw,bh,(int)(i.getX()+getLayoutX()),(int)(i.getY()+getLayoutY())+LRy,LR,Screen.Block_H-LRy*2)){
				if(i.getlife() == false) continue;
				System.out.println("【インベーダー】どっかーん");
				//スコア＋
				score = score+100;
				sc.setText("score:"+score);
				//貫通ボールでなかったら跳ね返る
				if(b.getSklFlg() != 2) {
					b.setVx(b.getVx() * -1); //x反転
					b.setVy(b.getVy() * 1); //y
				}
				//画像変更
				i.setlconBreak();
				//スコアアップ
				//Score.scoreUp(10);
				//そのインベーダlifeがfalse
				i.setLife(false);
				//ミサイルストップ
//				((Ball)s).stop();
				if(getLayoutY()<280&&invaderLifeCheck()==false) {
					//Ball.stop();
					lb.setText("GameClear");
					lb.setTextFill(Color.SKYBLUE);
					lb.setVisible(true);
					startBack.setVisible(true);
					nextStage.setVisible(true);
					Ball.life = Ball.life+1;
				}
			}
			//右に当たったら
			if(Screen.hitCheck2(bx,by,bw,bh,(int)(i.getX()+getLayoutX())+(Screen.Block_W-LR),(int)(i.getY()+getLayoutY())+LRy,LR,Screen.Block_H-LRy*2)){
				if(i.getlife() == false) continue;
				System.out.println("【インベーダー】どっかーん");
				//スコア＋
				score = score+100;
				sc.setText("score:"+score);
				//貫通ボールでなかったら跳ね返る
				if(b.getSklFlg() != 2) {
					b.setVx(b.getVx() * -1); //x反転
					b.setVy(b.getVy() * 1); //y
				}
				//画像変更
				i.setlconBreak();
				//スコアアップ
				//Score.scoreUp(10);
				//そのインベーダlifeがfalse
				i.setLife(false);
				//ミサイルストップ
//				((Ball)s).stop();
				if(getLayoutY()<280&&invaderLifeCheck()==false) {
					lb.setText("GameClear");
					lb.setTextFill(Color.SKYBLUE);
					lb.setVisible(true);
					startBack.setVisible(true);
					nextStage.setVisible(true);
					Ball.life = Ball.life+1;	
				}
			}
			//上下に当たったら
			else if(Screen.hitCheck2(bx,by,bw,bh,(int)(i.getX()+getLayoutX()),(int)(i.getY()+getLayoutY()),Screen.Block_W,Screen.Block_H)){
				if(i.getlife() == false) continue;
				System.out.println("【インベーダー】どっかーん");
				//スコア＋
				score = score+100;
				sc.setText("score:"+score);
				//貫通ボールでなかったら跳ね返る
				if(b.getSklFlg() != 2) {
					b.setVx(b.getVx() * 1); //x
					b.setVy(b.getVy() * -1); //y反転
				}
				//画像変更
				i.setlconBreak();
				//スコアアップ
				//Score.scoreUp(10);
				//そのインベーダlifeがfalse
				i.setLife(false);
				//ミサイルストップ
//				((Ball)s).stop();
				if(getLayoutY()<280&&invaderLifeCheck()==false) {
					lb.setText("GameClear");
					lb.setTextFill(Color.SKYBLUE);
					lb.setVisible(true);
					startBack.setVisible(true);
					nextStage.setVisible(true);
					Ball.life = Ball.life+1;	
				}
			}
		}
	}	
	
	boolean invaderLifeCheck(){
		for(Block i : inv) {
			if(i.getlife() == true) {
				return true;
			}
		}
		return false;
	}
	
	void reset() {
		int count = 0;
		int[][] hai = new int[][] {
			{0,0,0,1,1,1,0,0,0,0,1,1,0,0,0},
			{0,0,0,0,1,0,0,0,0,1,0,0,1,0,0},
			{0,0,0,0,1,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0,0,1,1,0,0,0},
			{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,1,0,0,0,0,1,0,0,1,0,0},
			{0,0,0,1,1,1,0,0,0,0,1,1,0,0,0}
		};
		
		for(int i=0;i<hai.length;i++) {
			for(int j=0;j<hai[i].length;j++) {
				if(hai[i][j] != 0) {
					inv.add(count,new Block(j*Screen.Block_W,i*Screen.Block_H));
					getChildren().add(inv.get(count));
					count++;
				}
			}
		}
	}
	
	void next(int stageFlg) {
		setLayoutX(Screen.BlockPane_X);
		setLayoutY(Screen.BlockPane_Y);
		lb.setVisible(false);
		//b.setVisible(false);
		if(stageFlg == 1) {
			int count = 0;
			int[][] hai = new int[][] {
				{1,1,1,0,0,1,0,0,0,1,0,1,1,1,0},
				{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
				{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
				{1,1,1,0,0,1,0,0,0,1,0,1,1,1,0},
				{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
				{1,0,0,1,0,1,0,0,0,1,0,1,0,0,1},
				{1,1,1,0,0,0,1,1,1,0,0,1,1,1,0}
			};
			
			for(int i=0;i<hai.length;i++) {
				for(int j=0;j<hai[i].length;j++) {
					if(hai[i][j] != 0) {
						inv.add(count,new Block(j*Screen.Block_W,i*Screen.Block_H));
						getChildren().add(inv.get(count));
						count++;
					}
				}
			}
		}else if(stageFlg >= 2) {
			int count = 0;
			int[][] hai = new int[][] {
				{0,1,0,0,0,0,0,1,0,0,0,0,0,1,0},
				{1,1,1,0,0,0,1,0,1,0,0,0,1,1,1},
				{0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
				{0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
				{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
				{0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
				{0,0,1,0,0,0,1,1,1,0,0,0,1,0,0},
				{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
			};
			
			for(int i=0;i<hai.length;i++) {
				for(int j=0;j<hai[i].length;j++) {
					if(hai[i][j] != 0) {
						inv.add(count,new Block(j*Screen.Block_W,i*Screen.Block_H));
						getChildren().add(inv.get(count));
						count++;
					}
				}
			}
		}
	}
}

