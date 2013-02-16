package uk.org.alienscience.routes;

import uk.org.alienscience.HttpRequest;

/**
 * A node that matches a literal sequence of bytes
 */
class LiteralNode implements Node {

    private final byte[] literal;
    private final DecisionTree tree;
   
    LiteralNode(byte[] literal, DecisionTree  tree) {
        this.literal = literal;
        this.tree = tree;
    }
    
    @Override
    public boolean matches(HttpRequest request, int start, int end) {
        byte[] path = request.getPath();
        
        for (int i = start; i < end; i++) {
            int j = i - start;
            if (j >= literal.length) return false;
            if (path[i] != literal[j]) return false;
        }
        return true;
    }

    @Override
    public DecisionTree getTree() {
        return tree;
    }

}
