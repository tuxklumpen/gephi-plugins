package org.gephi.plugin.CirclePack;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.openide.util.Lookup;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Matt
 */
public class LayoutComboBoxEditor extends AbstractComboBoxEditor
{
    public LayoutComboBoxEditor()
    {
        Map options = getPlacementMap(false);

        super.ComboValues = options;
    }


    public static Map getPlacementMap(boolean boolIncludeRandom)
    {
        GraphController graphController = Lookup.getDefault().lookup(GraphController.class);
        GraphModel objGraphModel   = graphController.getGraphModel();

        Map<String, String> map = new TreeMap<String, String>();
        if (boolIncludeRandom)
        {
            map.put("Random", "Random");
        }
        map.put("NodeID", "Node ID");
        map.put("Degree", "Degree");
        map.put("No Selection", "No Selection");
        if (objGraphModel != null)
        {
            if (objGraphModel.isDirected())
            {
                map.put("InDegree", "In Degree");
                map.put("OutDegree", "Out Degree");
            }
            for (Column c : objGraphModel.getNodeTable())
            {
                map.put(c.getId(), c.getTitle() + " (Attribute)");
            }
        }
        return map;
    }
}