package Random;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * DATE: 2022/5/28
 * CREATE BY: Byx
 */
public class Interview17_2 {
    public static void main(String[] args) {
        String[] input1 = {"Wunqax(96)", "Uzpk(82)", "Osh(35)", "Jtnh(18)", "Itzh(44)", "Uuhkq(55)", "Qdnelp(55)", "Wzjk(91)", "Ztapky(33)", "Ybzvzu(46)", "Imfn(72)", "Xrzs(71)", "Xcqh(61)", "Ydh(65)", "Wpwy(63)", "Usb(62)", "Nhfrbb(97)", "Wwhnyb(41)", "Amdz(52)", "Vtuwh(75)", "Qus(81)", "Jsgaug(47)", "Ophzz(75)", "Affzwm(1)", "Qvogv(84)", "Wybk(77)", "Swlia(52)", "Evtun(69)", "Isc(89)", "Uelrb(6)", "Ctlwjd(54)", "Shtvck(1)", "Emz(11)", "Sbh(24)", "Otacsp(96)", "Ejue(15)", "Hhw(60)", "Saou(61)", "Lrbba(51)", "Kiz(92)", "Szmnvu(14)", "Ejrxje(88)", "Ndhnm(23)", "Jsa(93)", "Cggsqc(89)", "Jlobv(21)", "Ejljt(62)", "Wyi(3)", "Anbt(71)", "Vrdmy(61)", "Clw(26)", "Oaimq(2)", "Yjeu(98)", "Jsbzzl(38)", "Mnihs(77)", "Qpizf(34)", "Ayaj(36)", "Gesl(26)", "Vzo(30)", "Wxait(93)", "Jze(20)", "Ajkgnh(8)", "Hka(31)", "Pbz(83)", "Wtnsu(89)", "Afkbg(54)", "Nkw(95)", "Bbz(65)", "Oqbk(24)", "Shl(97)", "Ylf(56)", "Ytg(29)", "Tusg(50)", "Fxhtzx(8)", "Tleub(17)", "Yin(66)", "Abhm(82)", "Eql(58)", "Vuvje(38)", "Nmr(31)", "Ykhhar(83)", "Taiin(79)", "Qap(5)", "Inrqp(50)", "Uyjfmh(28)", "Lmzsfi(55)", "Obk(24)", "Iml(9)", "Fapu(67)", "Bhubwi(6)", "Qdazt(77)", "Pmvbst(44)", "Krc(12)", "Jsowzy(79)", "Eksnn(2)", "Kqurnj(48)", "Phu(5)", "Gndk(96)", "Zruayf(3)", "Zwdtz(28)"};
        String[] input2 = {"(Wwhnyb,Jsowzy)", "(Ejue,Tleub)", "(Ybzvzu,Obk)", "(Qpizf,Wxait)", "(Mnihs,Shl)", "(Oqbk,Shl)", "(Osh,Gndk)", "(Ztapky,Fapu)", "(Wybk,Jsa)", "(Qus,Emz)", "(Ydh,Cggsqc)", "(Usb,Ejljt)", "(Jtnh,Iml)", "(Qpizf,Zwdtz)", "(Ykhhar,Gndk)", "(Ctlwjd,Sbh)", "(Jtnh,Jze)", "(Sbh,Saou)", "(Jtnh,Ejue)", "(Nkw,Ylf)", "(Uelrb,Ajkgnh)", "(Qvogv,Tusg)", "(Sbh,Shl)", "(Qvogv,Vrdmy)", "(Eksnn,Zruayf)", "(Xrzs,Pmvbst)", "(Ayaj,Vuvje)", "(Nhfrbb,Anbt)", "(Wpwy,Eksnn)", "(Gesl,Tusg)", "(Qvogv,Ayaj)", "(Itzh,Saou)", "(Jlobv,Tusg)", "(Wpwy,Oaimq)", "(Imfn,Eksnn)", "(Vtuwh,Tusg)", "(Ayaj,Pmvbst)", "(Abhm,Uyjfmh)", "(Swlia,Lmzsfi)", "(Uuhkq,Shtvck)"};

        System.out.println(Arrays.toString(new Solution().trulyMostPopular(input1, input2)));
    }
}


class Solution {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<String,Integer> map = new HashMap<>();
        int len = names.length;
        int[] nums = new int[len];
        String[] name = new String[len];
        int i = 0;
        for(String s : names){
            int in = s.indexOf('(');
            name[i] = s.substring(0,in);
            map.put(name[i],i);
            String num = s.substring(in+1,s.length()-1);
            nums[i++] = Integer.valueOf(num);
        }
        for(String s : synonyms){
            int in = s.indexOf(',');
            String name1 = s.substring(1,in);
            String name2 = s.substring(in+1,s.length()-1);
            if(!map.containsKey(name1)){
                map.put(name1,i++);
            }
            if(!map.containsKey(name2)){
                map.put(name2,i++);
            }
        }
        UnionFind uf = new UnionFind(i,nums,name);
        for(String s : synonyms){
            int in = s.indexOf(',');
            String name1 = s.substring(1,in);
            String name2 = s.substring(in+1,s.length()-1);
            if(map.containsKey(name1) && map.containsKey(name2)){
                uf.unite(map.get(name1),map.get(name2));
            }
        }
        return uf.trulyMostPopular();
    }
}
class UnionFind{
    int[] root = null;
    int[] size = null;
    int[] nums = null;
    String[] names = null;
    int setCount;
    int n;
    public UnionFind(int n,int[] nums,String[] name){
        root = new int[n];
        this.nums = nums;
        this.names = name;
        size = new int[n];
        setCount = n;
        for (int i = 0; i < n; ++i) {
            size[i] = 0;
            root[i] = i;
        }
    }
    public int find(int x){
        if(root[x] == x){
            return x;
        }
        return root[x] = find(root[x]);
    }
    public boolean unite(int x,int y){
        x = find(x);
        y = find(y);
        if(x == y) return false;
        if(size[x] < size[y] || x > nums.length-1){
            int c = y;
            y = x;
            x = c;
        }else if(size[x] == size[y]) size[x] += 1;
        root[y] = x;
        if(y < nums.length && x < nums.length){
            nums[x] += nums[y];
            if(names[x].compareTo(names[y]) > 0) names[x] = names[y];
        }
        setCount--;
        return true;
    }
    public int getCount() {
        for(int i=nums.length; i<n; i++){
            if(root[i] == i) setCount--;
        }
        return setCount;
    }
    public String[] trulyMostPopular(){
        String[] nameS = new String[getCount()];
        int j = 0;
        for(int i=0; i<root.length; i++){
            if(i == root[i]){
                nameS[j++] = names[i] + '(' + nums[i] + ')';
            }
        }
        Arrays.sort(nameS);

        return nameS;
    }
}
