package info.lynxnet.etudes.trac;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Form {
    private String name;
    private StringBuilder body = new StringBuilder();
    private int pointer = 0;
    private List<FormElement> elements = new ArrayList<>();

    public Form(String name, StringBuilder body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public StringBuilder getBody() {
        return body;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public List<FormElement> getElements() {
        return elements;
    }

    public boolean hasMarkers(int offset, int length) {
        return elements.stream().filter(elem ->
            elem instanceof FormMarker && elem.getOffset() >= offset && elem.getOffset() < offset + length
        ).count() > 0;
    }

    public void adjustOffsets(int start, int shift) {
        List<FormElement> afters = elements.stream().filter(elem ->
                elem instanceof FormMarker && elem.getOffset() >= start
        ).collect(Collectors.toList());
        afters.stream().forEach(elem -> elem.setOffset(elem.getOffset() + shift));
    }

    public int getLastMarkerOffsetInRange(int offset, int length) {
        Optional<FormElement> marker = elements.stream().filter(elem ->
                elem instanceof FormMarker && elem.getOffset() >= offset && elem.getOffset() < offset + length
        ).max((a, b) -> a.getOffset() - b.getOffset());
        return  marker.isPresent() ? marker.get().getOffset() : offset + length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Form{");
        sb.append("name='").append(name).append('\'');
        sb.append(", body=").append(body);
        sb.append(", pointer=").append(pointer);
        sb.append(", elements=").append(elements);
        sb.append('}');
        return sb.toString();
    }
}
