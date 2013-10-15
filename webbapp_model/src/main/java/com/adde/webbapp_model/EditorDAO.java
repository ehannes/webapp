package com.adde.webbapp_model;

import com.adde.webbapp_model_util.AbstractDAO;

/**
 *
 * @author ehannes
 */
public class EditorDAO extends AbstractDAO<SimpleEditorEntry, Long> {
    public static EditorDAO newInstance() {
        return new EditorDAO();
    }
    
    private EditorDAO(){ //String puName) {
        super(SimpleEditorEntry.class, ProjectPlatform.PU);
    }
    
}