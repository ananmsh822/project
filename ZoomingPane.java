package Gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class ZoomingPane extends Pane {
    Node content;
    private DoubleProperty zoomFactor = new SimpleDoubleProperty(1);

    ZoomingPane(Node content) {
        this.content = content;
       
        Scale scale = new Scale(1, 1);
        this.content.getTransforms().add(scale);

        zoomFactor.addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scale.setX(newValue.doubleValue()+0.2);
                scale.setY(newValue.doubleValue()+0.2);
                requestLayout();
             
            }
        });
    }

protected void layoutChildren() {
    Pos pos = Pos.TOP_LEFT;
    double width = getWidth();
    double height = getHeight();
    double top = getInsets().getTop();
    double right = getInsets().getRight();
    double left = getInsets().getLeft();
    double bottom = getInsets().getBottom();
    double contentWidth = (width - left - right)/zoomFactor.get();
    double contentHeight = (height - top - bottom)/zoomFactor.get();
    layoutInArea(content, left, top,
            contentWidth, contentHeight,
            0, null,
            pos.getHpos(),
            pos.getVpos());
}

public final Double getZoomFactor() {
    return zoomFactor.get();
}
public final void setZoomFactor(Double zoomFactor) {
    this.zoomFactor.set(zoomFactor);
}
public final DoubleProperty zoomFactorProperty() {
    return zoomFactor;
}
}
