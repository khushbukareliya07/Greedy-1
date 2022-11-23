//approach -1
/*
 * 1. queue for index
 * 2.mark visited with -1
 * 3. poll idx from the queue, run loop from 1 to Math.abs(nums[index polled])
 * 4. check if the new idx(= index polled + loops i) is n-1; if so we reached last index and return true, else keep going!
 * 5. if not visited add it to the queue
 * 6. TC -O(n); sc -O(n)
 */

class Solution {
    public boolean canJump(int[] nums) {
    
        if(nums == null || nums.length <2) return true;
        
        int n = nums.length;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        nums[0] = nums[0]* -1; //mark as visited
        
        while(!q.isEmpty())
        {
            int index = q.poll();
            
            for(int i=1; i <= Math.abs(nums[index]); i++)
            {
                int currIdx = index+i;
                if(currIdx == n-1) return true;
                
                if(nums[currIdx] >=0)
                {
                    q.add(currIdx);
                    nums[currIdx] *= -1;
                }
            }
        }
     return false;   
    }
}

/*approach -2
 * DFS
 * helper call with nums and index
 * //base case - if index == n-1 return true; 
 * 
 * //logic
 * //mark the index visited = *-1
 * make a recursive call only if the index is not visited
 * tc-O(n), sc - recursive stack - O(h) = height of the tree
 */
class Solution {
    int n;
    public boolean canJump(int[] nums) {
    
        if(nums == null || nums.length <2) return true;
        n = nums.length;
        return dfs(nums, 0);
    }

    private boolean dfs(int[] nums, int index)
    {
        //base case
        if(index == n-1) return true;

        //logic
        nums[index] *= -1;
        for(int i =1; i<= Math.abs(nums[index]); i++)
        {
            int idx = index + i;

            if(nums[idx] >0 && dfs(nums, idx))
                return true;
        }
        return false;
    }
}


//approach -3 Optimized
/*
 * 1. start from the 2nd last index
 * 2. last index  = destinantion, if that can be reach by any index from n-2 to 0; just update destination to that index and move on!
 * 3. at last if we get the destination ==0 ; i.e. we reached the end and so it's valid setup and return true else false. 
 tc -O(n), sc - O(1)
 */

class Solution {
    public boolean canJump(int[] nums) {
    
        if(nums == null || nums.length <2) return true;
        
        int n = nums.length;
        int destination = n-1; 
        
        for(int i = n-2; i>=0; i--)
        {
            if(nums[i] + i >=destination)
                destination =  i;
        }
        return destination == 0;    
    }
}

//approach -4
/*more optimized
 * 1. take a move/count variable = nums[0]
 * run loop from left to right;
 * 2. at any point moves <0 return false
 * 3. at any point moves >= n-1 return true
 * 4. saves unnecessary calculations if nums length is 5 and nums[0] =12!! or greater than 5!
 * tc - O(n), sc - O(1)
 */
class Solution {
    public boolean canJump(int[] nums) {
    
        if(nums == null || nums.length <2) return true;
        
        int n = nums.length;
        int moves = nums[0];
        
        for(int i = 1; i< n; i++)
        {
           moves--;
           
            if(moves <0) return false;
            if(moves >= n-1) return true;
            
            moves = Math.max(moves, nums[i]); 
        }
        return true;    
    }
}
 