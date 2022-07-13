public class dfsQuestions{
    
    //leetcode 200 No. of Islands
    public static void dfs_Islands(char [][]grid,int [][]dirs,int r,int c,boolean [][]vis){
        
        vis[r][c] = true;
        for(int d = 0;d<dirs.length;d++){
            int x = r + dirs[d][0];
            int y = c + dirs[d][1];
            
            if(x >= 0 && x < grid.length && y>=0 && y < grid[0].length && !vis[x][y] && grid[x][y] == '1')
                  dfs_Islands(grid,dirs,x,y,vis);  
        }
    }
    
    public int numIslands(char[][] grid) {
        int n = grid.length, m= grid[0].length;
        int totalIslands = 0;
        boolean vis [][] = new boolean[n][m];
        int dirs[][] = {{0,1},{1,0},{0,-1},{-1,0}};
        
        for(int i =0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(grid[i][j] == '1' && !vis[i][j]){
                    totalIslands++;
                    dfs_Islands(grid,dirs,i,j,vis);
                }
            }
        }
        return totalIslands;
    }

    
    
    //leetcode 695 max area of island
    public static int dfs_MaxIsland(int [][]grid,int r,int c,int n,int m,int [][]dirs){
      
        int count1s = 0;
        
        if(grid[r][c] == 1)
            count1s++;
        grid[r][c] = 0;
        for(int d =0 ;d<dirs.length;d++){
            int x = r + dirs[d][0];
            int y = c + dirs[d][1];
            
            if(x >=0 && y >=0 && x<n && y<m && grid[x][y] == 1){
                count1s += dfs_MaxIsland(grid,x,y,n,m,dirs);  
            }   
        }
        return count1s;
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int n = grid.length,m = grid[0].length;
        
        int [][]dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                if(grid[i][j] == 1){
                    maxArea = Math.max(maxArea,dfs_MaxIsland(grid,i,j,n,m,dirs));
                }
            }
        }
        return maxArea;
    }
}