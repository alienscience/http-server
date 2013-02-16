package uk.org.alienscience.routes;

import uk.org.alienscience.HttpRequest;

/**
 * Captures part of a path
 */
class CaptureNode implements Node {
    
    private final String name;
    private final DecisionTree tree;
    
    CaptureNode(String name, DecisionTree tree) {
        this.name = name;
        this.tree = tree;
    }
    
    @Override
    public boolean matches(HttpRequest request, int start, int end) {
        request.setPathParameter(name, start, end);
        return true;
    }

    @Override
    public DecisionTree getTree() {
        return tree;
    }

}
