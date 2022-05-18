package breakblock;

import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class Ball extends ImageView {
	
	//フィールド
	static private Image img = new Image(Paths.get(Screen.BallImg).toUri().toString());
	static private Image img1 = new Image(Paths.get(Screen.BallImg1).toUri().toString()); //スキル1画像
	static private Image img2 = new Image(Paths.get(Screen.BallImg2).toUri().toString()); //スキル2画像
	static private Image img3 = new Image(Paths.get(Screen.BallImg3).toUri().toString()); //スキル3画像
	Timeline timeline;
	PlayClip pClip;
	private boolean moveFlg = false;
	private double vx; //速度x(moveメソッドで初期化)
	private double vy;//速度y(moveメソッドで初期化)
	private int sklFlg = 0; //スキルを使ったか(なし：0, 遅延：1, 貫通：2, 増殖：3)
	Flag flg;
	static int gaugeFlg = 0; //ゲージ開始用フラグ
	static int life = 3; //ライフ
	Label ll; //ライフ用ラベル
	Label lb; //gameClear,gameOver用ラベル
	Button startBack;
	IncreasedBall ball2; //増殖スキル用ボール
	IncreasedBall ball3;

	//スキルボール用コンストラクタ
	Ball(){
		super(img);
		//位置設定
		this.setX(Screen.Ball_X);
		this.setY(Screen.Ball_Y);
		
		//タイムライン(スレッド)
		timeline = new Timeline(new KeyFrame(Duration.millis(3),event->{run();}));
		timeline.setCycleCount(Timeline.INDEFINITE);//永久に
		timeline.play();//スタート
	}
	
	//コンストラクタ
	Ball(Flag flg,Label ll,Label lb,Button startBack,IncreasedBall ball2,IncreasedBall ball3){
		super(img);
		this.flg = flg;
		this.ll = ll;
		this.lb = lb;
		this.startBack = startBack;
		this.ball2 = ball2;
		this.ball3 = ball3;
		pClip = new PlayClip("");//音
		
		//位置設定
		this.setX(Screen.Ball_X);
		this.setY(Screen.Ball_Y);
		
		//タイムライン(スレッド)
		timeline = new Timeline(new KeyFrame(Duration.millis(3),event->{run();}));
		timeline.setCycleCount(Timeline.INDEFINITE);//永久に
		timeline.play();//スタート
	}

	//スレッド実行中(ミサイル移動)
	private void run() {
		if(moveFlg) {
			//System.out.printf("【ミサイル位置】(%d,%d)\n",(int)getX(),(int)getY());
			//setY(getY()-1);
			gaugeFlg += 1;
			setX(getX() + this.vx); //x方向に進む
			setY(getY() + this.vy); //y方向に進む
			//観測者通知(UFOなどに)
			notifyObservers();
			
			//壁、天井にあたったら
			if(getX() <= 0 || getX() >= Screen.Right) { //左右の壁にあたったら
				this.vx = -vx;
			}else if(getY() <= 0) { //天井にあたったら
				this.vy = -vy;
			}
			//地面に当たったらボールがなくなる
			if(getY()>Screen.Height) {
				System.out.println("Life:"+life);
				stop();
				life = life-1;
				ll.setText("Life:"+life);
				
				if(life == 0) {
					lb.setVisible(true);
					lb.setText("GameOver");
					lb.setTextFill(Color.WHITE);
					startBack.setVisible(true);
					startBack.setLayoutX(400);
					life = life+3;
					System.out.println("Life:"+life);
				}
				//スキルを使っていたら
				if(this.sklFlg != 0) {
					Gauge.time = 0; //ゲージを0にする
					this.skillTimeOut(); //スキル切れ
					System.out.println(Gauge.time);
				}
			
				flg.setBallShotFlg(0); //発射フラグを0に
				
				if(this.ball2.isVisible() == true) { //ball2が画面上にあったら
					this.ball2.setVisible(false); //不可視化
				}if(this.ball3.isVisible() == true) { //ball3が画面上にあったら
					this.ball3.setVisible(false); //不可視化
				}
			}
		}
	}
	
	//ミサイル発射
	void move(double hx) {
		
		//ボールが発射されていなかったら
		if(flg.getBallShotFlg() == 0) {
			
			//減速スキル使用時
			if(sklFlg == 1) {
				this.vx = -0.5;
				this.vy = -0.5;
			}
			//スキルを使ってないor減速スキル以外使用時
			else {
				this.vx = -1;
				this.vy = -1;
			}
			
			setY((double)Screen.Ball_Y);//実際にそのポジションへ
			setX((hx + Screen.Bar_W/2));
			moveFlg = true;//動くフラグON
			flg.setBallShotFlg(1);
		}
		pClip.play();
		
		//System.out.printf("【発射時ミサイル位置】(%d,%d)\n",(int)getX(),(int)getY());
	}
	
	//スキル実行中
	void skillActive(Bar b) {
		if(Gauge.time >= 20 && sklFlg == 0) { //ゲージが溜まっていたら,重ね掛け無効
			
			//どのスキルを選択したか→スキルメソッド実行
			if(flg.getSkillButtonFlg() == 1) {
				slowBall();
			}else if(flg.getSkillButtonFlg() == 2) {
				penetrationBall();
			}else if(flg.getSkillButtonFlg() == 3) {
				increaseBall(b);
			}
			Gauge.time = 0; //ゲージを0にする
		}
	}
	
	//ミサイル飛ばない状態にする
	void stop() {
		moveFlg = false;
		setX(-100);
		setY(600);
	}
	
	//スキル：遅くなる球
	void slowBall() {
		this.sklFlg = 1;
		//球の色変更->青
		this.setIcon(Ball.img1);
		//球の速さを半分に
		this.vx /= 2;
		this.vy /= 2;
	}
	
	//スキル：貫通
	void penetrationBall() {
		this.sklFlg = 2;
		//球の色変更->赤
		this.setIcon(Ball.img2);
	}

	//スキル：増える球
	void increaseBall(Bar b) {
		this.sklFlg = 3;
		//球の色変更->黄
		this.setIcon(Ball.img3);
		//処理
		if(this.ball2.isVisible() == false) { //ball2が画面上になかったら
			this.ball2.setVisible(true); //可視化
			this.ball2.move(b.getX());
		}else if(this.ball3.isVisible() == false) { //ball3が画面上になかったら
			this.ball3.setVisible(true); //可視化
			this.ball3.move(b.getX());
		}
		
	}
	
	//スキル切れ
	void skillTimeOut() {
		this.sklFlg = 0;
		//球の色変更->通常
		this.setIcon(Ball.img);
	}
	
	//-----------observer-----------
	//画像変更
	void setIcon(Image img) {
		setImage(img);
	}
	
	private ArrayList<Observer> observers = new ArrayList<>(); // Observer たちを保持
	
	// Observerを追加
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	 
	// Observerを削除
	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}
	
	// Observerへ通知
	public void notifyObservers() {
		for(Observer o : observers){
			//登録された Observer 全員に通知
			o.update(this);
		}
	}
	//-----------observer-----------
	
	//Getter, Setter
	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}
	
	public int getSklFlg() {
		return sklFlg;
	}

	public void setSklFlg(int sklFlg) {
		this.sklFlg = sklFlg;
	}
	
}
