package ObjectEngin.HagrmaSys.ComonKarakuri.MapKarakuri;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import ObjectEngin.HagrmaSys.Hagrma;
import ObjectEngin.HagrmaSys.Karakuri;
import ObjectEngin.HagrmaSys.ComonKarakuri.ColligionKarakuri.CollionHagrma;

/**
 * 地図カラクリ（マップカラクリ）
 * 属性：特殊カラクリ
 * プライオリティ：１
 * マップクラス用に作成したカラクリです
 * マップクラスを使用する場合はこれをインスタンス化しオブジェクトマネージャーに登録してください
 *  */
public class MapKarakuri extends Karakuri{

	public MapKarakuri() {
		super();
		priority = -1;
	}
	@Override
	public String call() throws Exception {
		 LinkedList<Hagrma> hagrmas = objM.getHagrmas(MapHagrma.class);
		 LinkedList<Hagrma> colhagrmas = objM.getHagrmas(CollionHagrma.class);
		for(Hagrma hagrma : hagrmas){
			if(hagrma instanceof MapHagrma)
				if(colhagrmas != null && !colhagrmas.isEmpty())
					for(Hagrma colhag : colhagrmas){
						if(colhag instanceof CollionHagrma){
							boolean bool = ((MapHagrma)hagrma).isHit(((CollionHagrma)colhag));

							if(bool){
								boolean[] pushF = new boolean[4];
								boolean[][] checkMap = ((MapHagrma)hagrma).getCheckMap();
								if(checkMap.length > 1 && checkMap[0].length > 1){
									double vecx = colhag.getParent().getBoundBox().x-colhag.getParent().getOldBox().x;
									double vecy = colhag.getParent().getBoundBox().y-colhag.getParent().getOldBox().y;
									double chipsize = ((MapHagrma)hagrma).getChipsize();
									Rectangle copyoldBox = new Rectangle();
									copyoldBox.setBounds(colhag.getParent().getOldBox());
									Rectangle copyBoundBox = new Rectangle();
									copyBoundBox.setBounds(colhag.getParent().getBoundBox());
									double setx = copyBoundBox.x%chipsize;
									double sety = copyBoundBox.y%chipsize;
									double mapPosx = copyBoundBox.x-setx;
									double mapPosy = copyBoundBox.y-sety;
									
									if(isLeftline(checkMap)){
										if((int)copyoldBox.getMinX() > (int)(mapPosx+chipsize) && vecx < 0)
										pushF[0] = true;
									}
									if(isRightline(checkMap)){
										if((int)copyoldBox.getMaxX() <=  (int)(mapPosx+(chipsize*(checkMap.length-1)))&& vecx > 0)
										pushF[1] = true;
									}
									if(isUpline(checkMap)){
										if((int)copyoldBox.getMinY() >=  (int)(mapPosy+chipsize) && vecy < 0)
										pushF[2] = true;
									}
									if(isDownline(checkMap)){
										if((int)copyoldBox.getMaxY()-1 < (int)(mapPosy+(chipsize*(checkMap[0].length-1)))&& vecy >= 0)
										pushF[3] = true;
									}
									
									if(isleftup(checkMap)){
										if(vecx < 0){
											if(vecy < 0){	
												if(islineCross(copyBoundBox.getLocation(), copyoldBox.getLocation(),
														new Point((int)(mapPosx+chipsize), (int)(mapPosy+chipsize)),
														new Point((int)(mapPosx+chipsize), (int)(mapPosy)))){
//												if(vecy/vecx < (chipsize-sety)/(chipsize-setx)){
													pushF[0] = true;
												}else{
													pushF[2] = true;
												}
											}else{
												if((int)copyoldBox.getMinX() > (int)(mapPosx+chipsize))
												pushF[0] = true;
											}
										}else{
											if((int)copyoldBox.getMinY() >=  (int)(mapPosy+chipsize) && vecy < 0)
											pushF[2] = true;
										}
									}
									if(isrightup(checkMap)){
										if(vecx > 0){
											if(vecy < 0){
												if(islineCross(
													new Point((int)copyBoundBox.getMaxX(), (int)copyBoundBox.getMinY()),				//｜　□　□｜□｜
													new Point((int)copyoldBox.getMaxX(), (int)copyoldBox.getMinY()),					//｜　□　□　□｜
													new Point((int)(mapPosx+(chipsize*(checkMap.length-1))), (int)(mapPosy+chipsize)),	//｜　□　□　□｜
													new Point((int)(mapPosx+(chipsize*(checkMap.length-1))), (int)(mapPosy)))){			//
//												if(vecy/vecx >= (chipsize-sety)/(setx)){
													pushF[1] = true;
												}else{
													pushF[2] = true;
												}
											}else{
												if((int)copyoldBox.getMaxX() <=  (int)(mapPosx+(chipsize*(checkMap.length-1))))
												pushF[1] = true;
											}
										}else{
											if((int)copyoldBox.getMinY() >=  (int)(mapPosy+chipsize) && vecy < 0)
											pushF[2] = true;
										}
									}
									if(isleftdown(checkMap)){
										if(vecx < 0){
											if(vecy >= 0){
												if(islineCross(
													new Point((int)copyBoundBox.getMinX(), (int)copyBoundBox.getMaxY()),
													new Point((int)copyoldBox.getMinX(), (int)copyoldBox.getMaxY()),
													new Point((int)(mapPosx+(chipsize)), (int)(mapPosy+(chipsize*(checkMap[0].length-1)))),
													new Point((int)(mapPosx+(chipsize)), (int)(mapPosy+(chipsize*(checkMap[0].length)))))){
//												if(vecy/vecx >= (sety)/(chipsize-setx)){
													pushF[0] = true;
												}else{
//													if(isecolpos(new Point((int)copyBoundBox.getMinX(), (int)copyBoundBox.getMaxY()),
//															new Point((int)copyoldBox.getMinX(), (int)copyoldBox.getMaxY()))){
//														pushF[0] = true;
//													}else
													pushF[3] = true;
												}
											}else{
												if((int)copyoldBox.getMinX() > (int)(mapPosx+chipsize))
												pushF[0] = true;
											}
										}else{
											if((int)copyoldBox.getMaxY()-1 < (int)(mapPosy+(chipsize*(checkMap[0].length-1))) && vecy >= 0)
											pushF[3] = true;
										}
									}
									if(isrightdown(checkMap)){
										if(vecx > 0){
											if(vecy >= 0){
												if(islineCross(
														new Point((int)copyBoundBox.getMaxX()-1, (int)copyBoundBox.getMaxY()),
														new Point((int)copyoldBox.getMaxX()-1, (int)copyoldBox.getMaxY()),
														new Point((int)(mapPosx+(chipsize*(checkMap.length-1))), (int)(mapPosy+(chipsize*(checkMap[0].length-1)))),
														new Point((int)(mapPosx+(chipsize*(checkMap.length-1))), (int)(mapPosy+(chipsize*(checkMap[0].length)))))){
													pushF[1] = true;
												}else{
													pushF[3] = true;
												}
											}else{
												if((int)copyoldBox.getMaxX()<(int)(mapPosx+(chipsize*(checkMap.length-1))))
												pushF[1] = true;
											}
										}else{
											if((int)copyoldBox.getMaxY()-1 < (int)(mapPosy+(chipsize*(checkMap[0].length-1))) && vecy >= 0)
											pushF[3] = true;
										}
									}

//									((CollionHagrma)colhag).getParent().boxrefresh();

								}else{
									if(checkMap.length == 1 && checkMap[0].length == 1){
										colhag.getParent().reverse();
									}else{
										if(checkMap.length == 1){
											if(checkMap[0][0])
												pushF[2] = true;
											if(checkMap[0][checkMap[0].length-1])
												pushF[3] = true;
										}
										if(checkMap[0].length == 1){
											if(checkMap[0][0])
												pushF[0] = true;
											if(checkMap[checkMap.length-1][0])
												pushF[1] = true;
													
										}
									}
									
								}

								if(pushF[0]){
//									System.out.println("右にプッシュ");
									((MapHagrma)hagrma).push_right((CollionHagrma)colhag);
									((CollionHagrma) colhag).RightPushed();
								}
								if(pushF[1]){
//									System.out.println("左にプッシュ");
									((MapHagrma)hagrma).push_left((CollionHagrma)colhag);
									((CollionHagrma) colhag).LeftPushed();
								}
								if(pushF[2]){
//									System.out.println("したにプッシュ");
									((MapHagrma)hagrma).push_down((CollionHagrma)colhag);
									((CollionHagrma) colhag).DownPushed();
								}
								if(pushF[3]){
//									System.out.println("上にプッシュ");
									((MapHagrma)hagrma).push_up((CollionHagrma)colhag);
									((CollionHagrma) colhag).UpPushed();
								}
							}
						}
					}
		}
		colhagrmas = null;
		hagrmas = null;
		return super.call();
	}
	private boolean isLeftline(boolean[][] checkMap){
		boolean flag = false;
		if(checkMap.length > 0){
			if(checkMap[0].length > 2)
				for(int cnt = 1;cnt < checkMap[0].length-1;cnt++){
					if(checkMap[0][cnt]){
						flag = true;
						break;
					}
				}
			if(flag == false){
				if(checkMap[0].length == 1)
					flag = checkMap[0][0];
				if(checkMap[0].length == 2)
					flag = checkMap[0][0]&&checkMap[0][1];
			}
		}
		return flag;
	}
	private boolean isRightline(boolean[][] checkMap){
		boolean flag = false;
		if(checkMap.length > 0){
			if(checkMap[0].length > 2)
				for(int cnt = 1;cnt < checkMap[0].length-1;cnt++){
					if(checkMap[checkMap.length-1][cnt]){
						flag = true;
						break;
					}
				}
			if(flag == false){
				if(checkMap[checkMap.length-1].length == 1)
					flag = checkMap[checkMap.length-1][0];
				if(checkMap[checkMap.length-1].length == 2)
					flag = checkMap[checkMap.length-1][0]&&checkMap[checkMap.length-1][1];
			}
		}
		return flag;
	}
	private boolean isUpline(boolean[][] checkMap){
		boolean flag = false;
		if(checkMap.length > 0){
			if(checkMap[0].length > 0){
				if(checkMap.length > 2)
					for(int cnt = 1;cnt < checkMap.length-1;cnt++){
						if(checkMap[cnt][0]){
							flag = true;
							break;
						}
					}
				if(flag == false){
					if(checkMap.length == 1)
						flag = checkMap[0][0];
					if(checkMap.length == 2)
						flag = checkMap[0][0]&&checkMap[1][0];
				}
			}
		}
		return flag;
	}
	private boolean isDownline(boolean[][] checkMap){
		boolean flag = false;
		if(checkMap.length > 0){
			if(checkMap[0].length > 0){
				if(checkMap.length > 2)
					for(int cnt = 1;cnt < checkMap.length-1;cnt++){
						if(checkMap[cnt][checkMap[0].length-1]){
							flag = true;
							break;
						}
					}
				if(flag == false){
					if(checkMap.length == 1)
						flag = checkMap[0][checkMap[0].length-1];
					if(checkMap.length == 2)
						flag = checkMap[0][checkMap[0].length-1]&&checkMap[1][checkMap[0].length-1];
				}
			}
		}
		return flag;
	}
	private boolean isleftup(boolean[][] checkMap){
		return checkMap[0][0]&!checkMap[1][0]&!checkMap[0][1];
	}
	private boolean isrightup(boolean[][] checkMap){
		return checkMap[checkMap.length-1][0]&!checkMap[checkMap.length-2][0]&!checkMap[checkMap.length-1][1];
	}
	private boolean isleftdown(boolean[][] checkMap){
		return checkMap[0][checkMap[0].length-1]&!checkMap[1][checkMap[0].length-1]&!checkMap[0][checkMap[0].length-2];
	}
	private boolean isrightdown(boolean[][] checkMap){
		return checkMap[checkMap.length-1][checkMap[0].length-1]&!checkMap[checkMap.length-2][checkMap[0].length-1]&!checkMap[checkMap.length-1][checkMap[0].length-2];
	}

	private boolean islineCross(Point A,Point B,Point C,Point D){

		boolean flag1 = (((B.getX()-A.getX())*(C.getY()-A.getY())-((B.getY()-A.getY())*(C.getX()-A.getX())))*
				(((B.getX()-A.getX())*(D.getY()-A.getY()))-((B.getY()-A.getY())*(D.getX()-A.getX()))) <= 0);
		boolean flag2 = (((D.getX()-C.getX())*(A.getY()-C.getY())-((D.getY()-C.getY())*(A.getX()-C.getX())))*
				(((D.getX()-C.getX())*(B.getY()-C.getY()))-((D.getY()-C.getY())*(B.getX()-C.getX()))) <= 0);
		
		return flag1 && flag2;
	}
	private boolean isecolpos(Point A,Point B){
		return (A.x == B.x && A.y == B.y);
	}
}
