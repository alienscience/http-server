package uk.org.alienscience.routes;

import uk.org.alienscience.HttpRequest;

public interface Node {

    /**
     * Indicates if the given node matches part of a path
     * @param request  The path to match against
     * @param start The start index of the part to match against
     * @param end The end index + 1 of the part to match against
     * @return True if the node matches the path, false otherwise
     */
    public boolean matches(HttpRequest request, int start, int end);
   
    /**
     * Get the tree contained by this node
     */
    public DecisionTree getTree();
}
