package uk.org.alienscience.routes;

import uk.org.alienscience.HttpRequest;

/**
 * Matches anything
 */
class WildCardNode implements Node {

    private final DecisionTree tree;
   
    WildCardNode(DecisionTree tree) {
        this.tree = tree;
    }
    
    @Override
    public boolean matches(HttpRequest request, int start, int end) {
        return true;
    }

    @Override
    public DecisionTree getTree() {
        return tree;
    }

}
