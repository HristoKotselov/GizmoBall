package model;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import enums.Connections_Status;

public class Connections {

	private Map<Integer, Set<AGizmoComponent>> keyMap;

	private Map<AGizmoComponent, Set<AGizmoComponent>> triggerMap;

	public Connections(Map<Integer, Set<AGizmoComponent>> kMap, Map<AGizmoComponent, Set<AGizmoComponent>> gtMap) {
		keyMap = kMap;
		triggerMap = gtMap;
	}
	
	public Connections(){
		keyMap = new HashMap<Integer, Set<AGizmoComponent>>();
		triggerMap = new HashMap<AGizmoComponent, Set<AGizmoComponent>>();
	}

	/**
	 * TODO
	 * @param keyCode
	 * @param gizmo
	 * @return 
	 */
	public Connections_Status.AC addKeyConnection(int keyCode, AGizmoComponent gizmo) {
		// parameter validation
		if(gizmo == null ||
			KeyEvent.getKeyText(keyCode).contains("Unknown keyCode")		// check if keyCode refers to a fake key
		){
			return Connections_Status.AC.INVALID_ARG;
		}
		
		
		Set<AGizmoComponent> gizmoSet = keyMap.get(keyCode);
		if(gizmoSet == null){		// i.e. check if there is no recorded connection for this key yet
			gizmoSet = new HashSet<AGizmoComponent>();
		}
		

		if(!gizmoSet.add(gizmo)){		// i.e. does connection already exist...?
			return Connections_Status.AC.CONNECTION_EXIST;
		}
		
		keyMap.put(keyCode, gizmoSet);
		
		return Connections_Status.AC.OK;
	}

	public Connections_Status.RC removeKeyConnection(int keyCode, AGizmoComponent gizmo) {
		// parameter validation
		if(gizmo == null ||
			KeyEvent.getKeyText(keyCode).contains("Unknown keyCode")		// check if keyCode refers to a fake key
		){
			return Connections_Status.RC.INVALID_ARG;
		}
		
		Set<AGizmoComponent> gizmoSet = keyMap.get(keyCode);
		
		if(gizmoSet == null)
			return Connections_Status.RC.CONNECTION_NOT_EXIST;
		else{
			if(!gizmoSet.remove(gizmo)){		// i.e. check if there is no recorded connection for this key yet
				return Connections_Status.RC.CONNECTION_NOT_EXIST;
			}
			keyMap.put(keyCode, gizmoSet);
		}
		
		return Connections_Status.RC.OK;
	}

	public Set<AGizmoComponent> getKeyConnections(int keyCode) {
		return keyMap.get(keyCode);
	}
	
	public void clearKeyConnection(int keyCode){
		Set<AGizmoComponent> gizmoSet = keyMap.get(keyCode);
		gizmoSet.clear();
	}
	
	public void clearALLKeyConnection() {
		keyMap.clear();
	}
	

	public  Connections_Status.AC addGizmoTriggerConnection(AGizmoComponent triggeredGizmo, AGizmoComponent reactionGizmo) {
		// parameter validation
		if(triggeredGizmo == null || reactionGizmo == null){
			return Connections_Status.AC.INVALID_ARG;
		}
		
		
		Set<AGizmoComponent> gizmoSet = triggerMap.get(triggeredGizmo);
		if(gizmoSet == null){		// i.e. check if there is no recorded connection for this key yet
			gizmoSet = new HashSet<AGizmoComponent>();
		}
		

		if(!gizmoSet.add(reactionGizmo)){		// i.e. does connection already exist...?
			return Connections_Status.AC.CONNECTION_EXIST;
		}
		
		triggerMap.put(triggeredGizmo, gizmoSet);
		
		return Connections_Status.AC.OK;
	}

	public Connections_Status.RC removeGizmoTriggerConnection(AGizmoComponent triggeredGizmo, AGizmoComponent reactionGizmo) {
		// parameter validation
		if(triggeredGizmo == null || reactionGizmo == null){
			return Connections_Status.RC.INVALID_ARG;
		}
		
		Set<AGizmoComponent> gizmoSet = triggerMap.get(triggeredGizmo);
		
		if(gizmoSet == null)
			return Connections_Status.RC.CONNECTION_NOT_EXIST;
		else{
			if(!gizmoSet.remove(reactionGizmo)){		// i.e. check if there is no recorded connection for this key yet
				return Connections_Status.RC.CONNECTION_NOT_EXIST;
			}
			triggerMap.put(triggeredGizmo, gizmoSet);
		}
		
		return Connections_Status.RC.OK;
	}

	public Set<AGizmoComponent> getGizmoTriggerConnections(AGizmoComponent gizmo) {
		return triggerMap.get(gizmo);
	}

	public void clearTriggerConnection(AGizmoComponent gizmo){
		Set<AGizmoComponent> gizmoSet = keyMap.get(gizmo);
		gizmoSet.clear();
	}
	
	public void clearALLGizmoTriggerConnection() {
		triggerMap.clear();
	}

}
