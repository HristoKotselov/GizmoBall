package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connections {

	private Map<Integer, List<AGizmoComponent>> keyMap;

	private Map<AGizmoComponent, List<AGizmoComponent>> triggerMap;

	public Connections(Map<Integer, List<AGizmoComponent>> kMap, Map<AGizmoComponent, List<AGizmoComponent>> gtMap) {
		keyMap = kMap;
		triggerMap = gtMap;
	}

	public boolean addKeyConnection(Integer keyPress, AGizmoComponent gizmo) {
		// TODO some validation
		return false;

	}

	public boolean removeKeyConnection(Integer keyPress, AGizmoComponent gizmo) {
		// TODO some validation
		return false;

	}

	public void clearKeyConnection() {
		// TODO

	}

	public List<AGizmoComponent> getKeyConnections(Integer keyPress) {
		// TODO
		return null;

	}

	public boolean addGizmoTriggerConnection() {
		// TODO some validation
		return false;

	}

	public boolean removeGizmoTriggerConnection() {
		// TODO some validation
		return false;

	}

	public void clearGizmoTriggerConnection() {
		// TODO 

	}

	public List<AGizmoComponent> getGizmoTriggerConnections(AGizmoComponent gizmo) {
		// TODO
		return null;

	}
}
