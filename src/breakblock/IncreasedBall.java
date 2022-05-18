package breakblock;

//import java.nio.file.Paths;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import javafx.scene.image.Image;
import javafx.util.Duration;

//増殖スキル使用時　増えたボールクラス
public class IncreasedBall extends Ball{
	
	//フィールド
//	static private Image img = new Image(Paths.get(Screen.BallImg).toUri().toString());
	Timeline timeline;
	PlayClip pClip;
	private boolean moveFlg = false;
	private double vx; //速度x(moveメソッドで初期化)
	private double vy;//速度y(moveメソッドで初期化)

	//コンストラクタ
	IncreasedBall(){
		super();
//		super(img);
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
		}
	}
	
	//ミサイル発射
	void move(double hx) {
		
		this.vx = -1;
		this.vy = -1;
		
		setY((double)Screen.Ball_Y);//実際にそのポジションへ
		setX((hx + Screen.Bar_W/2));
		moveFlg = true;//動くフラグON
		
		pClip.play();
		
		//System.out.printf("【発射時ミサイル位置】(%d,%d)\n",(int)getX(),(int)getY());
	}
	
	//ミサイル飛ばない状態にする
	void stop() {
		moveFlg = false;
		setX(-100);
		setY(600);
	}
	
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
	
}