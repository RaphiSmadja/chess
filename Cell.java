package A1D;

import A1D.elements.Element;

public class Cell {
    private Position position = null;
    private boolean isEmpty;
    private Element element;

    public Cell(boolean isEmpty, Element element, Position position) {
        this.isEmpty = isEmpty;
        this.element = element;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
