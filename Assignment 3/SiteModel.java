public class SiteModel {
        private boolean room;
        private int max;
        private int space;
        private String resourceName;
        private int resourceAmount;
        private int travelCost;
        private int tile; //0 = free, 1 = line tile (pay 3 exploration coins), 2 = double line tile (pay 6 exploration coins)
        
        public SiteModel(String resource, int resAmt, int t){
                max = (Math.random() <= 0.5) ? 1 : 2;
                travelCost = (Math.random()*8)+1; //travel cost, 1 = foot,  2 = car, 3 = boat, 4 = plane, 5-8 is double of each ex: 5 is 2 foot icons.
                resourceAmount = resAmt;
                resourceName = resource;
                space = 0;
                tiles = t;
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
