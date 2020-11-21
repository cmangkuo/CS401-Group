public class SiteModel {
        private boolean room;
        private int max;
        private int space;
        private String resourceName;
        private int resourceAmount;
        private int travelCost;
        
        public SiteModel(String resource, int resAmt){
                max = (Math.random() <= 0.5) ? 1 : 2;
                travelCost = (Math.random()*8)+1; //travel cost, 1 = foot,  2 = car, 3 = boat, 4 = plane, 5-8 is double of each ex: 5 is 2 foot icons.
                resourceName = resource;
                resourceAmount = resAmt;
                space = 0;
        }
        
        public String getResourceName(){
                return resourceName;
        }
        
        public int getResourceAmount(){
                return resourceAmount;
        }
        
        public int getTravelCost(){
                return travelCost;
        }
        
        public void archaeologistDigging(){
                space++;
        }
        
        public void releaseArchaeologist(){
                space --;
        }
        
        public boolean hasRoom()
        {
                if (space < max) 
                        return true;
                return false;
        }
}
