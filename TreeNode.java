private class TreeNode {
  int id;
  String city;
  TreeNode l;
  TreeNode r;
  TreeNode p;
  int N;
  List booklist;

  TreeNode(int id, String city) {
    this.id = id;
    this.city = city;
  }

  TreeNode(int id, String city, TreeNode p) {
    this.id = id;
    this.city = city;
    this.p = p;
  }

  TreeNode getRight() {
    return r;
  }

  TreeNode getLeft() {
    return l;
  }

  void getTreeSize() {
    return N;
  }

  String getCity() {
    return city;
  }

  List getList(){
    return List;
  }

  void setLeft(TreeNode l) {
    this.l = l;
    N++;
  }

  void setRight(TreeNode r) {
    this.r = r;
    N++;
  }
}
