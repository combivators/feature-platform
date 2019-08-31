package net.tiny.feature.model;

import java.util.List;

public class Settings {
	public List<Entry> entries;
	public int width = 480;
	public int height= 480;
	public float radius = 0.75f; //'75%',
	public int radiusMin = 75;
	public boolean bgDraw = true;
	public String bgColor = "#FFF";
    //透明度设置
	public float opacityOver = 1.00f;
	public float opacityOut = 0.05f;
	public float opacitySpeed = 6;
    //最小缩放因子
	public float scaling = 0.60f;
	public int fov = 800;
    public float speed = 0.2f;
    public String fontFamily = "Oswald, Arial, sans-serif";
    public int fontSize = 15;
    public String fontColor = "#111";
    public String fontWeight = "normal"; //bold
    public String fontStyle = "normal";  //italic
    public String fontStretch = "normal";//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
    public boolean fontToUpperCase = true;
    public String tooltipFontFamily = "Oswald, Arial, sans-serif";
    public int tooltipFontSize = 11;
    public String tooltipFontColor = "#111";
    public String tooltipFontWeight = "normal"; //bold
    public String tooltipFontStyle = "normal";  //italic
    public String tooltipFontStretch = "normal";//wider, narrower, ultra-condensed, extra-condensed, condensed, semi-condensed, semi-expanded, expanded, extra-expanded, ultra-expanded
    public boolean tooltipFontToUpperCase = false;
    public String tooltipTextAnchor = "left";
    public int tooltipDiffX = 0;
    public int tooltipDiffY = 10;
    public boolean lineDraw = true;
}
