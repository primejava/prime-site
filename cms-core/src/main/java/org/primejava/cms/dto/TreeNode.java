package org.primejava.cms.dto;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements Serializable, Comparable {
	public static final String ROOT_TYPE = "root";

	private String id;
	private String text;
	private String iconCls;
	private Boolean leaf;
	private String icon;
	private Boolean checked;
	private Integer index;
	private Boolean expanded;
	private String cls;
	private String parentId;
	private Integer sort;

	private List<TreeNode> children;

	public TreeNode() {
	}

	/**
	 * Creates a new instance of TreeNode.
	 * 
	 * @param id
	 * @param text
	 * @param iconCls
	 * @param operType
	 */

	public TreeNode(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public TreeNode(String id, String text, String iconCls) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
	}

	public TreeNode(String id, String text, String iconCls, Boolean leaf) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.leaf = leaf;
	}

	public TreeNode(String id, String text, Boolean leaf) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	// public static void main(String[] args) {
	// System.out.println(new Gson().toJson(new TreeNode("1", "text", "adfasdf",
	// NodeTypeEnum.ROOT)));
	// System.out.println("ROOT".equals(NodeTypeEnum.ROOT.toString()));
	// }

	/**
	 * id.
	 * 
	 * @return the id
	 * @since JDK 1.6
	 */
	public String getId() {
		return id;
	}

	/**
	 * text.
	 * 
	 * @return the text
	 * @since JDK 1.6
	 */
	public String getText() {
		return text;
	}

	/**
	 * iconCls.
	 * 
	 * @return the iconCls
	 * @since JDK 1.6
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * leaf.
	 * 
	 * @return the leaf
	 * @since JDK 1.6
	 */
	public Boolean getLeaf() {
		return leaf;
	}

	/**
	 * checked.
	 * 
	 * @return the checked
	 * @since JDK 1.6
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * id.
	 * 
	 * @param id
	 *            the id to set
	 * @since JDK 1.6
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * text.
	 * 
	 * @param text
	 *            the text to set
	 * @since JDK 1.6
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * iconCls.
	 * 
	 * @param iconCls
	 *            the iconCls to set
	 * @since JDK 1.6
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * leaf.
	 * 
	 * @param leaf
	 *            the leaf to set
	 * @since JDK 1.6
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * checked.
	 * 
	 * @param checked
	 *            the checked to set
	 * @since JDK 1.6
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
	 * index.
	 * 
	 * @return the index
	 * @since JDK 1.6
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * index.
	 * 
	 * @param index
	 *            the index to set
	 * @since JDK 1.6
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * expanded.
	 * 
	 * @return the expanded
	 * @since JDK 1.6
	 */
	public Boolean getExpanded() {
		return expanded;
	}

	/**
	 * expanded.
	 * 
	 * @param expanded
	 *            the expanded to set
	 * @since JDK 1.6
	 */
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	/**
	 * icon.
	 * 
	 * @return the icon
	 * @since JDK 1.6
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * icon.
	 * 
	 * @param icon
	 *            the icon to set
	 * @since JDK 1.6
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	/**
	 * parentId.
	 * 
	 * @return the parentId
	 * @since JDK 1.7
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * parentId.
	 * 
	 * @param parentId
	 *            the parentId to set
	 * @since JDK 1.7
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof TreeNode)) {
			return false;
		}
		TreeNode node = (TreeNode) obj;
		if (!this.getId().equals(node.getId())) {
			return false;
		}
		if (!this.getParentId().equals(node.getParentId())) {
			return false;
		}
		return true;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @author xs
	 * @param o
	 * @return
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (null == o) {
			return -1;
		}
		if (!(o instanceof TreeNode)) {
			return -1;
		}
		TreeNode node = (TreeNode) o;
		if (null == this.getSort() || null == node.getSort()) {
			return 1;
		}
		if (this.getSort() > node.getSort()) {
			return 1;
		}
		if (this.getSort() < node.getSort()) {
			return -1;
		}
		return 0;
	}
}
