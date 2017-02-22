package info.lynxnet.etudes.trac;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Form implements Serializable {
    private String name;
    private StringBuilder body = new StringBuilder();
    private int pointer = 0;
    private List<FormMarker> markers = new ArrayList<>();

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

    public List<FormMarker> getMarkers() {
        return markers;
    }

    public boolean hasMarkers(int offset, int length) {
        return markers.stream().filter(elem ->
                elem.getOffset() >= offset && elem.getOffset() < offset + length
        ).count() > 0;
    }

    public void adjustOffsets(int start, int shift) {
        List<FormMarker> afters = markers.stream().filter(elem -> elem.getOffset() >= start
        ).collect(Collectors.toList());
        afters.stream().forEach(elem -> elem.setOffset(elem.getOffset() + shift));
    }

    public int getLastMarkerOffsetInRange(int offset, int length) {
        Optional<FormMarker> marker = markers.stream().filter(
                elem -> elem.getOffset() >= offset && elem.getOffset() < offset + length
        ).max((a, b) -> a.getOffset() - b.getOffset());
        return marker.isPresent() ? marker.get().getOffset() : offset + length;
    }

    public int getClosestMarkerOffset(int offset) {
        Optional<FormMarker> marker = markers.stream().filter(
                elem -> elem.getOffset() > offset
        ).min((a, b) -> a.getOffset() - b.getOffset());
        return marker.isPresent() ? marker.get().getOffset() : body.length();
    }

    public List<FormElement> segment() {
        List<FormElement> result = new ArrayList<>();
        List<FormMarker> sorted = markers.stream().sorted(
                (a, b) -> a.getOffset() - b.getOffset()).collect(Collectors.toList());
        for (int i = 0; i < sorted.size(); i++) {
            int current = sorted.get(i).getOffset();
            int prev = (i > 0) ? sorted.get(i - 1).getOffset() : 0;
            if (prev != current) {
                FormSegment segment = new FormSegment(body.substring(prev, current), prev);
                result.add(segment);
            }
            result.add(sorted.get(i));
        }
        if (sorted.size() > 0) {
            FormMarker last = sorted.get(sorted.size() - 1);
            if (last.getOffset() < body.length()) {
                FormSegment segment = new FormSegment(body.substring(last.getOffset()), last.getOffset());
                result.add(segment);
            }
        } else {
            FormSegment segment = new FormSegment(body.toString(), 0);
            result.add(segment);
        }

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Form{");
        sb.append("name='").append(name).append('\'');
        sb.append(", body=").append(body);
        sb.append(", pointer=").append(pointer);
        sb.append(", markers=").append(markers);
        sb.append('}');
        return sb.toString();
    }
}
