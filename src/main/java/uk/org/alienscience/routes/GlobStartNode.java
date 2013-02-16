package uk.org.alienscience.routes;

import uk.org.alienscience.HttpRequest;

/**
 * Matches glob patterns that start with '*' e.g *.html
 */
class GlobStartNode implements Node {

    private final byte[] literal;
    private final DecisionTree tree;
   
    GlobStartNode(byte[] literal, DecisionTree tree) {
        this.literal = literal;
        this.tree = tree;
    }
    
    @Override
    public boolean matches(HttpRequest request, int start, int end) {
        byte[] path = request.getPath();
        
        for (int i = end -1; i >= start; i++) {
            int j = i - start;
            if (j < 0) return false;
            if (path[i] != literal[j]) return false;
        }
        return true;
    }

    @Override
    public DecisionTree getTree() {
        return tree;
    }

}
