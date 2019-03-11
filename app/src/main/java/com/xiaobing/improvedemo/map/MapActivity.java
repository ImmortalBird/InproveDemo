package com.xiaobing.improvedemo.map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapOptions;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.util.LogUtil;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/11
 */
public class MapActivity extends BaseActivity {

    private MapView map;
    private GraphicsLayer graphicsLayer;
    private static final String DEFAULT_BASE_MAP_SERVICE_URL = "http://services.arcgisonline.com/ArcGIS/rest/services/ESRI_StreetMap_World_2D/MapServer";
    private String mapServerUrl = "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";
    /**
     * 是否设置了起点
     */
    private boolean mIsSetStart = false;
    private Polyline polyline;
    private RadioGroup rg;
    private RadioButton rbPoint, polygon, line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_map);
        super.onCreate(savedInstanceState);
        // 声明一个 map 对象
        map = findViewById(R.id.map);
        rg = findViewById(R.id.rg);
        rbPoint = findViewById(R.id.rb_point);
        line = findViewById(R.id.rb_line);
        polygon = findViewById(R.id.rb_polygon);
        setTitle(R.string.ID_map_01_01);

        /*
        * 为 RadioGroup 设置监听器
        **/
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_point:
                    // 点击 描点
                    /*
                    * TODO: 2019/3/11 判断当前图层是否为描点图层
                    *   是：不做动作
                    *   不是：移除当前图层，并添加一个描点图层
                    *
                     */
                    break;
                case R.id.rb_line:
                    //
                    break;
                case R.id.rb_polygon:
                    break;
                default:
                    break;
            }
        });
        setUpMap();
    }


    private void setUpMap() {
        if (map != null) {
            // 给 map 增加配置
            MapOptions mapOptions = new MapOptions(MapOptions.MapType.SATELLITE);
            // 中心点
            mapOptions.setCenter(30.2872234998, 120.0115407685);
            // 缩放比例
            mapOptions.setZoom(10);
            // 将设置添加到map中
            map.setMapOptions(mapOptions);
            map.setMapBackground(Color.WHITE, Color.TRANSPARENT, 0, 0);
            // 添加一个图层
            map.addLayer(new ArcGISDynamicMapServiceLayer(DEFAULT_BASE_MAP_SERVICE_URL));
            // map.addLayer(new  ArcGISTiledMapServiceLayer(mapServerUrl));

            graphicsLayer = new GraphicsLayer();
            map.addLayer(graphicsLayer);
            map.setOnSingleTapListener(new OnSingleTapListener() {
                @Override
                public void onSingleTap(float x, float y) {
                    //x,y就是屏幕上点击的坐标
                    LogUtil.print("x = " + x + "   y = " + y);
                    drawLine(x, y);
                    markPoint(x, y);

                }
            });

        }
    }

    private void drawLine(float x, float y) {
        Point point = map.toMapPoint(x, y);
        if (polyline == null) {
            polyline = new Polyline();
        }
        /*
         * 是否设置了起点？
         *   如果设置了：
         *       从上个点向当前点画线
         *   如果没有设置：
         *       将当前点设置为起始点
         **/
        if (!mIsSetStart) {
            polyline.startPath(point);
            mIsSetStart = true;
        } else {
            polyline.lineTo(point);
        }
        SimpleLineSymbol sms = new SimpleLineSymbol(Color.BLUE, 2, SimpleLineSymbol.STYLE.SOLID);
        Graphic graphic = new Graphic(polyline, sms);
        graphicsLayer.addGraphic(graphic);
    }

    private void markPoint(float x, float y) {
        //把屏幕坐标转化为地图上的坐标点
        Point point = map.toMapPoint(x, y);
        //点的样式
        SimpleMarkerSymbol sms = new SimpleMarkerSymbol(Color.RED, 16, SimpleMarkerSymbol.STYLE.CIRCLE);
        //把点和样式组合成一个Graphic，显示图形的载体
        Graphic graphic = new Graphic(point, sms);
        //添加到图层上显示
        graphicsLayer.addGraphic(graphic);
        //mGraphicsLayer.addGraphic(graphic)返回的是一个int id值，表示操作的ID，删除Graphic需要用到
        //int id = mGraphicsLayer.addGraphic(graphic)
        //删除相应的addGraphic操作
        //mGraphicsLayer.removeGraphic（int id）
    }
}
