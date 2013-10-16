package com.adde.webbapp_model_dao;

import com.adde.webbapp_model.SimpleEditorEntry;
import com.adde.webbapp_model_util.AbstractDAO;

/**
 *
 * @author ehannes
 */
public class EditorDAO extends AbstractDAO<SimpleEditorEntry, Long> {
    public static EditorDAO newInstance() {
        return new EditorDAO();
    }
    
    private EditorDAO(){
        super(SimpleEditorEntry.class);
    }
    
}