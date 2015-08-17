package generation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import tree.DefaultTreeNodeSymbol;
import tree.TreeNode;

public class Message {

	public static final int WARNING = 1;
	public static final int ERROR = 2;
	
	public int type;
	public TreeNode node;
	public String message;

	public Message(int type, TreeNode node, String message) {
		this.type = type;
		this.node = node;
		this.message = message;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append("\033[31m");
		if (type == WARNING) {
			sb.append("Warning: ");
		} else {
			sb.append("Error: ");
		}
		sb.append(message);
		//sb.append("\033[0m");
		sb.append('\n');
		
		if (node == null)
			return sb.toString();
		
		sb.append(" ");
		if (node.getRealLeft() == null) {
			sb.append("unknown location");
		} else {
			sb.append(node.getRealLeft().getUnit()+": "+node.getRealLeft().getLine()+"/"+node.getRealLeft().getColumn());
		}
		if (node.getRealRight() != null) {
			sb.append(" - ");
			sb.append(node.getRealRight().getLine()+"/"+(node.getRealRight().getColumn()+1));
		}
		sb.append("\n");
		
		if (node instanceof DefaultTreeNodeSymbol) {
			sb.append(" at symbol ");
			DefaultTreeNodeSymbol symbol = (DefaultTreeNodeSymbol) node;
			sb.append(symbol.name);
			if (symbol.value != null) {
				sb.append(" ("+symbol.value+")");
			}
			sb.append("\n");
		}
		
		if (node.getRealLeft() != null && node.getRealLeft().getUnit() != null) {
			String path = node.getRealLeft().getUnit();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
				
				for (int i=1; i<node.getRealLeft().getLine(); i++) {
					reader.readLine();
				}
				
				int n_lines = (node.getRealRight() != null ? node.getRealRight().getLine() : node.getRealLeft().getLine());
				n_lines -= node.getRealLeft().getLine();

				for (int i=0; i<=n_lines; i++) {
					if (i > 1 && i+1 < n_lines) {
						// skip some lines
						reader.readLine();
						if (i == 2)
							sb.append("...\n");
						continue;
					}
					sb.append(reader.readLine());
					sb.append("\n");
				}
				
				reader.close();
			} catch (IOException e) {
			}
		}
		
		return sb.toString();
	}
}
