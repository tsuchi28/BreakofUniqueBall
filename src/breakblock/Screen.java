package breakblock;

public class Screen {
	//画面
	public static final int Width = 1017;	//幅
	public static final int Height = 700;	//高さ
	public static final int Right = 1000;	//右
	public static final int Left = 0;		//左
	public static final int Top = 50;		//天井
	public static final int Bottom = 700;	//地面
	
	//バー
	public static final int Bar_Y = 600;		//バーY
	public static final int Bar_W = 70;		//バー幅
	public static final int Bar_H = 15;		//バー高さ
	
	//ボール
	public static final int Ball_Y = 500;	//球Y
	public static final int Ball_X = -100;	//球X
	public static final int Ball_W = 11;		//球幅
	public static final int Ball_H = 11;		//球高さ
	
	//スコア
	public static final int Score_X = 0;		//スコアX
	public static final int Score_Y = 0;		//スコアY
	
	//ブロック
	public static final int Block_W = 50;	//ブロック幅
	public static final int Block_H = 30;	//ブロック高さ
	
	//ブロック群
	public static final int BlockPane_X = 100;	//X
	public static final int BlockPane_Y = 100;	//Y
	public static final int BlockPane_W = 800;	//幅
	public static final int BlockPane_H = 300;	//高さ
	
	//スキルゲージ
	public static final int Gauge_X = 400;	//X
	public static final int Gauge_Y = 10;	//Y
	public static final int Gauge_W = 200;	//幅
	public static final int Gauge_H = 30;	//高さ
	
	//ライフ
	public static final int Life_X = 800;	//X
	public static final int Life_Y = 10;		//Y
	
	//画像ファイルパス
	public static final String bgImg = "bubImg/bgBlack.png";	//背景画像(1000x700)
	public static final String bgImg2 = "bubImg/bgBlack2.png";//背景画像(1000x700)
	public static final String BarImg = "bubImg/barImg.png";				//バー画像
	public static final String BallImg = "bubImg/ballImg_11x11.png";		//ボール通常画像
	public static final String BallImg1 = "bubImg/ballImg_Blue_11x11.png";	//ボールスキル1画像
	public static final String BallImg2 = "bubImg/ballImg_Red_11x11.png";	//ボールスキル2画像
	public static final String BallImg3 = "bubImg/ballImg_Yellow_11x11.png";//ボールスキル3画像
	public static final String BlockImg1 = "bubImg/blockImg.png";			//ブロック正常画像
	public static final String BlockImg2 = "bubImg/blockImg2.png";			//ブロック壊れ画像
	public static final String GaugeImg1 = "bubImg/gauge0.png";
	public static final String GaugeImg2 = "bubImg/gauge1.png";
	public static final String GaugeImg3 = "bubImg/gauge2.png";
	public static final String GaugeImg4 = "bubImg/gauge3.png";
	public static final String GaugeImg5 = "bubImg/gauge4.png";
	
	//音声ファイルパス
	public static final String se1 = "";	//se
	public static final String se2 = "";	//se
	public static final String se3 = "";	//se
	public static final String se4 = "";	//se
	public static final String se5 = "";	//se
	public static final String bgm1 = "";	//bgm
	public static final String bgm2 = "";	//bgm

	//当たり判定の共通メソッド　ボール(bx,by) 当たるもの(x,y,w,h)
	/*static boolean hitCheck(int bx,int by,int x, int y, int w,int h) {
		if(x < bx && bx < x + w && y < by && by < y + h)return true;
		
		return false;
	}*/
	
	//当たり判定(範囲)
	static boolean hitCheck(int bx,int by,int bw,int bh,int x,int y,int w,int h) {
		if(((bx>x&&bx<x+w)||(x>bx&&x<bx+bw))&&((by>y&&by<y+h)||(y>by&&y<by+bh))) {
			return true;
		}else {
			return false;
		}
	}
	
	//当たり判定(ボールの真ん中を基準とする)
	static boolean hitCheck2(int bx,int by,int bw,int bh,int x,int y,int w,int h) {
		int bxx = bx + (bw-1)/2; //x方向の真ん中の座標
		int byy = by + (bh-1)/2; //y方向の真ん中の座標
		if(x < bxx && bxx < x + w && y < byy && byy < y + h) {
			return true;
		}else {
			return false;
		}
	}
	
}