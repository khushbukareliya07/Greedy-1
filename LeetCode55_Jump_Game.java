//approach -1
/*
 * 1. queue for index
 * 2.mark visited with -1
 * 3. poll idx from the queue, run loop from 1 to Math.abs(nums[index polled])
 * 4. check if the new idx(= index polled + loops i) is n-1; if so we reached last index and return true, else keep going!
 * 5. if not visited add it to the queue
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