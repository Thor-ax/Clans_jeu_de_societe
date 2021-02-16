package strats;
import clans.Terrain;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


/**
 * Note (Anthony Fleury) : 
 * Cette strategie est a modifier et a adapter pour que vous puissiez rendre la votre.
 * Pour la modifier, avec le bouton droit, faite un Refactor, Rename et donnez lui le nom de votre classe 
 * StratX avec X votre numero de rendu. 
 * Une fois fait, completez CORRECTEMENT les methodes getName et getGroupes.
 * Vous n'aurez ensuite qu'a completer le code de votre propre strategie, a vous de jouer !
 */



/**
 * @author humeau
 */
public class Strat46 implements Strategie{
    Random rand;
    int NbVillage;
    Terrain[] __plateau;
    
    public Strat46 (){
        super();
        rand= new Random();
        NbVillage = 0;
    }

    @Override
    public int[] mouvement(Terrain [] _plateau, int _myColor, int [] _colorScore, int _myScore, int _opponentScore, int [] _opponentMov, int [] _opponentVillages){
        int[] res= new int[2];
        int nbChoix= Tools.getNbSourceValide(_plateau);                         //on r�cup�re le nb de source valide     
        
        // ci dessous on initialise toutes les sources et destinations , ceci afin de pouvoir les classer par ordre de priotit�
        int src= 0;
        int dest = 0;
        int src_prio_1= 0;
        int dest_prio_1 = 0;
        int src_prio_2= 0;
        int dest_prio_2 = 0;
        int src_prio_3= 0;
        int dest_prio_3 = 0;
        int src_prio_4= 0;
        int dest_prio_4 = 0;
        int src_prio_5= 0;
        int dest_prio_5 = 0;
        int src_prio_6= 0;
        int dest_prio_6 = 0;
        int src_prio_7= 0;
        int dest_prio_7 = 0;
        int src_prio_8= 0;
        int dest_prio_8 = 0;
        int src_prio_9= 0;
        int dest_prio_9 = 0;
        int src_prio_10= 0;
        int dest_prio_10 = 0;
        int src_prio_11= 0;
        int dest_prio_11 = 0;
        int src_prio_12= 0;
        int dest_prio_12 = 0;
        int src_prio_13= 0;
        int dest_prio_13 = 0;
        int src_prio_14= 0;
        int dest_prio_14 = 0;
        int src_prio_15= 0;
        int dest_prio_15 = 0;
        int src_prio_16= 0;
        int dest_prio_16 = 0;
        
        // initialisation des scores possibles
        int score_1 = 0;
        int score_2 = 0;
        int score_3 = 0;
        int score_4 = 0;
        int score_5 = 0;
        int score_6 = 0;
        int score_7 = 0;
        int score_8 = 0;
        int score_9 = 0;
        int score_10 = 0;
        int score_11 = 0;
        
        int[] sourcesPossibles = Tools.getSource(_plateau);                     // tableau de toutes les sources possibles
        int nbSourcesPossibles = sourcesPossibles.length;                       // Nombre de sources possibles
        int [] sources_melangees = melange(sourcesPossibles);                   //Parcourir al�atoirement les sources possibles afin de ne pas �tre pr�visible
        NbVillage = Tools.countVillage(_plateau);                               // Nombre de village sur le plateau actuel
        __plateau = _plateau;                                                   // On r�cup�re le plateau pour la fonction ordre
        for(int k = 0; k< nbSourcesPossibles; k++){                             // Parcours de toutes les sources possibles une par une al�atoirement
            int source = sources_melangees[k];
            int nbVoisins = Tools.getNbVoisinDispo(_plateau, source);           // Nombre de voisins de la source actuelle
            
            if(nbVoisins == 1 ){                                                //Un terrain � un seul voisin -> village � cr�er (d�composition de tous les cas possibles ci dessous)
                
                int voisin = Tools.getVoisinsDispo(_plateau, source)[0];        //Le seul voisin dispo
                
                if(source != voisin){                                           // On s'assure de la diff�rence entre la case de la source et celle du voisin (pour �viter de faire planter au cas o�)
                    
                    if(Tools.getNbVoisinDispo(_plateau, voisin) == 1){          // SI la source ET le voisin n'ont qu'un seul voisin tous les 2 : il faut choisir o� construire le village
                        
                       
                        if(SurBonus(_plateau, voisin,0) ){                         //On fait le village sur un bonus si possible
                            int q[] = Tools.listeVillagesCreesSi(_plateau, source);
                            int t[] = ordre(q);
                            if(Tools.evaluerGain(_plateau, source, voisin, t)[_myColor] >= score_1){
                                src_prio_1 = source;
                                dest_prio_1 = voisin;
                                score_1 = Tools.evaluerGain(_plateau, src_prio_1, dest_prio_1, t)[_myColor];
                            }
                        }
                        
                        else if(SurBonus(_plateau, source,0) && _plateau[voisin].getNbCabane() < 7){  //Idem
                            int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                            int t[] = ordre(q);
                             if( Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_2){
                                src_prio_2 = voisin;
                                dest_prio_2 = source;
                                score_2 = Tools.evaluerGain(_plateau, src_prio_2, dest_prio_2, t)[_myColor];
                             }
                        }
                        
                        if(!SurMalus(_plateau, source) && _plateau[voisin].getNbCabane() < 7){  //On �vite de faire un village sur un malus
                            int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                            int t[] = ordre(q);
                            if(Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_3){
                                src_prio_3 = voisin;
                                dest_prio_3 = source;
                                score_3 = Tools.evaluerGain(_plateau, src_prio_3, dest_prio_3, t)[_myColor];
                            }
                        }
                        
                        else if(SurMalus(_plateau, voisin) && _plateau[voisin].getNbCabane() < 7  ){  //Idem
                            int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                            int t[] = ordre(q);
                            if(Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_4){
                                src_prio_4 = voisin;
                                dest_prio_4 = source;
                                score_4 = Tools.evaluerGain(_plateau, src_prio_4, dest_prio_4, t)[_myColor];
                            }
                        }
                        
                        
                        else {                                                  //On cr�� le village quand m�me (si aucun des cas ci-dessus) pour gagner des points 
                            int q[] = Tools.listeVillagesCreesSi(_plateau, source);
                            int t[] = ordre(q);
                            if(Tools.evaluerGain(_plateau, source, voisin, t)[_myColor] >= score_5){
                                src_prio_5 = source;
                                dest_prio_5 = voisin;
                                score_5 = Tools.evaluerGain(_plateau, src_prio_5, dest_prio_5, t)[_myColor];
                            }
                        }
                        
                        
                    }
                     //La source � un voisin, le voisin plus d'un voisin
                    else if(SurMalus(_plateau,source) && _plateau[source].getCabanes(_myColor)+_plateau[voisin].getCabanes(_myColor) > 1 ){ 
                        
                        //Je peux faire un village avec mes cabanes mais il est sur un malus-> On ne le cr�� pas
                        int q[] = Tools.listeVillagesCreesSi(_plateau, source);
                        int t[] = ordre(q);
                        if(Tools.evaluerGain(_plateau, source, voisin, t)[_myColor] >= score_6){
                            src_prio_6= source;
                            dest_prio_6 = voisin;
                            score_6 = Tools.evaluerGain(_plateau, src_prio_6, dest_prio_6, t)[_myColor];
                        }
  
                    }
                    
                    else if(_plateau[voisin].getCabanes(_myColor) == 0  && _plateau[source].getCabanes(_myColor) >=2 && _plateau[voisin].getNbCabane() < 7){
                        //Aucune cabane de ma couleur sur la source mais au moins 2 sur son seul voisin -> je cr�� le village
                        int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                        int t[] = ordre(q);
                        if(Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_7){
                            dest_prio_7 = source;
                            src_prio_7 = voisin;
                            score_7 = Tools.evaluerGain(_plateau, src_prio_7, dest_prio_7, t)[_myColor];
                        }
                    }
                    
                    else if(_plateau[source].getCabanes(_myColor) >= 2 && _plateau[voisin].getNbCabane() < 7){
                        //Au moins 2 cabanes de ma couleur sur la source, je ram�ne le voisin quelqu'il soit
                        int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                        int t[] = ordre(q);
                        if(Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_8){
                            src_prio_8 = voisin;
                            dest_prio_8 = source;
                            score_8 = Tools.evaluerGain(_plateau, src_prio_8, dest_prio_8, t)[_myColor];
                        }
                    }
                    
                    
                    
                    
                    else if( _plateau[voisin].getCabanes(_myColor) == 1 && _plateau[voisin].getNbCabane() < 7 && _plateau[source].getCabanes(_myColor) ==0 && _plateau[source].getNbCabane() <= 3){
                        // Une seule cabane de ma couleur sur la source et aucune sur le voisin: je compte le nombre de cabane pou' m'assurer de gagner des points
                        int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                        int t[] = ordre(q);
                        if(Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_9){
                        
                            dest_prio_9 = source;
                            src_prio_9 = voisin;
                            score_9 = Tools.evaluerGain(_plateau, src_prio_9, dest_prio_9, t)[_myColor];
                        }
                    }
                    
                    else if(_plateau[voisin].getCabanes(_myColor) == 0 && _plateau[voisin].getNbCabane() < 7 && Tools.getNbVoisinDispo(_plateau, voisin) > 1 && _plateau[source].getNbCabane() == 1){
                        // Aucune cabane de ma couleur mais une seule cabane au total -> je cr�� le village quand m�me, je n'offre qu'un point � un adversaire et en gagne 1
                        source = voisin;
                        int n = Tools.getVoisinsDispo(_plateau, source)[rand.nextInt(nbVoisins)];
                        if(n != source){
                            int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                            int t[] = ordre(q);
                            if(Tools.evaluerGain(_plateau, voisin, n, t)[_myColor] >= score_10){
                                dest_prio_10 = n;
                                src_prio_10 = voisin;
                                score_10 = Tools.evaluerGain(_plateau, src_prio_10, dest_prio_10, t)[_myColor];
                             }
                            
                        }
                    }
                    
                    else{
                        if(_plateau[voisin].getNbCabane() < 7){
                            //enfin je cr�� le village (au cas o� aucun des cas pr�c�dent n'est rempli)
                            int q[] = Tools.listeVillagesCreesSi(_plateau, voisin);
                            int t[] = ordre(q);
                            if(Tools.evaluerGain(_plateau, voisin, source, t)[_myColor] >= score_11){
                                src_prio_11 = voisin;
                                dest_prio_11 = source;
                                score_11 = Tools.evaluerGain(_plateau, src_prio_11, dest_prio_11, t)[_myColor];
                            }
                            
                        }
                    }
                    
                    
                }     
                
                
            }
            
            
            
            
            
            
            //On ne peut pas cr�er de village, on offre aucune possibilit� � l'adversaire 1) -> une case source avec plus de 2 voisins
            else if (nbVoisins >= 2 ){                                          
                
                //Priorit� = ne laisser aucune de mes cabanes seules sans offir un coup � l'adversaire
                int [] voisinsDispos = Tools.getVoisinsDispo(_plateau, source);
                
                if(PlusDeTroisVoisins(_plateau, source, nbVoisins,4 ) ){        //Une source dont tous les voisins ont au moins 4 voisins
                    src_prio_12 = source;
                    int n = Tools.getVoisinsDispo(_plateau, src_prio_12)[rand.nextInt(nbVoisins)];
                    int MaxIter = 0;
                    while(_plateau[n].getNbCabane() >= 7 && MaxIter <= 15){
                        n = Tools.getVoisinsDispo(_plateau, src_prio_12)[rand.nextInt(nbVoisins)];
                        MaxIter += 1;
                        
                        
                    }
                    
                    if(_plateau[n].getNbCabane() < 7){
                        dest_prio_12 = n;
                    }
                }
                else if(PlusDeTroisVoisins(_plateau, source, nbVoisins, 3) ){   //Une source dont tous les voisins ont au moins 3 voisins
                    src_prio_13 = source;
                    int n = Tools.getVoisinsDispo(_plateau, src_prio_13)[rand.nextInt(nbVoisins)];
                    int MaxIter = 0;
                    while(_plateau[n].getNbCabane() >= 7 && MaxIter <= 15){
                        n = Tools.getVoisinsDispo(_plateau, src_prio_13)[rand.nextInt(nbVoisins)];
                        MaxIter += 1;
                    }
                    if(_plateau[n].getNbCabane() < 7){
                        dest_prio_13 = n;
                        
                    }
                }
                
                else if(PlusDeTroisVoisins(_plateau, source, nbVoisins, 2) ){   //Une source dont tous les voisins ont au moins 2 voisins
                    src_prio_14 = source;
                    int n = Tools.getVoisinsDispo(_plateau, src_prio_14)[rand.nextInt(nbVoisins)];
                    int MaxIter = 0;
                    while(_plateau[n].getNbCabane() >= 7 && MaxIter <= 15){
                        n = Tools.getVoisinsDispo(_plateau, src_prio_14)[rand.nextInt(nbVoisins)];
                        MaxIter += 1;
                        
                    }
                    if(_plateau[n].getNbCabane() < 7){
                        dest_prio_14 = n;
                    }
                    
                }
            }
            
            
            
            
            
            
            if(_plateau[source].getCabanes(_myColor) == 0 ){                    
            //Une case ne contenant aucune cabane de ma couleur -> voir parmis les voisins si je peux y mettre une cabane de ma couleur
                for( int p = 0; p< nbVoisins; p++){
                    int voisin_p = Tools.getVoisinsDispo(_plateau, source)[p];
                    if(_plateau[voisin_p].getCabanes(_myColor) >=1 && _plateau[voisin_p].getNbCabane() < 7){
                        src_prio_15 = voisin_p;
                        dest_prio_15 = source;
                    }
                }
            }
            
            
            if(_plateau[source].getCabanes(_myColor) == 1){                     
            //Une case qui a une seule cabane de ma couleur -> La d�placer vers une autre case contenant des cabanes de ma couleur
                int []voisins = Tools.getVoisinsDispo(_plateau, source);
                for(int i = 0; i< nbVoisins ; i++){
                    int voisin = voisins[i];
                    if(_plateau[voisin].getCabanes(_myColor)>=1){
                        src_prio_16 = source;
                        dest_prio_16 = voisin;
                    }
                }
                
            }
            
            
        }
        
        //On met les return dans l'ordre de priorit� des coups les plus int�ressants au moins int�ressants
        
        
        if(dest_prio_1 != 0 && src_prio_1 !=  0){
            res[0] = src_prio_1;
            res[1] = dest_prio_1;
            //System.out.println("Coup 1");
            return res;
        }
        if(dest_prio_2 != 0 && src_prio_2 !=  0){
            res[0] = src_prio_2;
            res[1] = dest_prio_2;
            //System.out.println("Coup 2");
            return res;
        }
        if(dest_prio_3 != 0 && src_prio_3 !=  0){
            res[0] = src_prio_3;
            res[1] = dest_prio_3;
            //System.out.println("Coup 3");
            return res;
        }
        if(dest_prio_4 != 0 && src_prio_4 !=  0){
            res[0] = src_prio_4;
            res[1] = dest_prio_4;
            //System.out.println("Coup 4");
            return res;
        }
        if(dest_prio_5 != 0 && src_prio_5 !=  0){
            res[0] = src_prio_5;
            res[1] = dest_prio_5;
            //System.out.println("Coup 5");
            return res;
        }
        if(dest_prio_6 != 0 && src_prio_6 !=  0){
            res[0] = src_prio_6;
            res[1] = dest_prio_6;
            //System.out.println("Coup 6");
            return res;
        }
        if(dest_prio_7 != 0 && src_prio_7 !=  0){
            res[0] = src_prio_7;
            res[1] = dest_prio_7;
            //System.out.println("Coup 7");
            return res;
        }
        if(dest_prio_8 != 0 && src_prio_8 !=  0){
            res[0] = src_prio_8;
            res[1] = dest_prio_8;
            //System.out.println("Coup 8");
            return res;
        }
        if(dest_prio_9 != 0 && src_prio_9 !=  0){
            res[0] = src_prio_9;
            res[1] = dest_prio_9;
            //System.out.println("Coup 9");
            return res;
        }
        if(dest_prio_10 != 0 && src_prio_10 !=  0){
            res[0] = src_prio_10;
            res[1] = dest_prio_10;
           //System.out.println("Coup 10");
            return res;
        }
        
        if(dest_prio_11 != 0 && src_prio_11 !=  0){
            res[0] = src_prio_11;
            res[1] = dest_prio_11;
            //System.out.println("Coup 11");
            return res;
        }
        if(dest_prio_14 != 0 && src_prio_14 !=  0){
            res[0] = src_prio_14;
            res[1] = dest_prio_14;
            //System.out.println("Coup 14");
            return res;
        }
        if(dest_prio_13 != 0 && src_prio_13 !=  0){
            res[0] = src_prio_13;
            res[1] = dest_prio_13;
            //System.out.println("Coup 13");
            return res;
        }
        if(dest_prio_12 != 0 && src_prio_12 !=  0){
            res[0] = src_prio_12;
            res[1] = dest_prio_12;
            //System.out.println("Coup 12");
            return res;
        }
        if(dest_prio_15 != 0 && src_prio_15 !=  0){
            res[0] = src_prio_15;
            res[1] = dest_prio_15;
            //System.out.println("Coup 15");
            return res;
        }
        if(dest_prio_16 != 0 && src_prio_16 !=  0){
            res[0] = src_prio_16;
            res[1] = dest_prio_16;
            //System.out.println("Coup 16");
            return res;
        }
        else{
            //SI vraiment aucunes de TOUTES ces possibilit�es, on joue random (pour ne pas faire planter dans ce cas TRES rare)
            src= Tools.getSource(_plateau)[rand.nextInt(nbChoix)];              
            int nbVoisin= Tools.getNbVoisinDispo(_plateau, src);         
            dest= Tools.getVoisinsDispo(_plateau, src)[rand.nextInt(nbVoisin)];
            //System.out.println("random");
        }
        //on retourne notre selection
        res[0]=src;
        res[1]=dest;
        return res;
    }
    

    @Override
    public int[] ordre( int[] _villages){   
        ArrayList ordre_creation = new ArrayList();
        if(Tools.age(NbVillage) != Tools.age(NbVillage + _villages.length)){    // C'est-�-dire si on change d'�ge
            for(int k = 0; k < _villages.length; k++){
                if(SurBonus(__plateau, _villages[k],ordre_creation.size())){
                    ordre_creation.add(_villages[k]);                           //On cr�� en priorit� ceux qui donne un bonus
                }
            }
            if(ordre_creation.size() != _villages.length){
                for(int k = 0; k<_villages.length; k++){
                    if(!test(ordre_creation, _villages[k])){
                        ordre_creation.add(_villages[k]);
                    }
                    
                }
            }
        }
        
        
        
        else{
            Random rand = new Random();
            int a,tmp;
            // on melange le tableau des villages
            for (int i=0; i<_villages.length; i++){
                a=rand.nextInt(_villages.length);
                tmp=_villages[a];
                _villages[a]=_villages[i];
                _villages[i]=tmp;
                }
        }
        
       
        return _villages;
    }
    
    /**
     * Remplissez cette m�thode avec le bon format expliqu� ci-dessous
     * @return le nom des �l�ves (sous le format NOM1_NOM2) 
     * NOM1 et NOM2 sont uniquement les noms de famille de votre binome
     */
    public String getName(){
        return "VILLETTE_CALCOEN_PARIS_MORIN";
    }

    public String getGroupe(){
        return "1";
    }
    
    public boolean estVoisinDe(int source, int dest, Terrain[] plateau) {       // source et dest sont-ils vraiment voisin -> Algo de v�rification pour corriger des bugs
        int []t = Tools.getVoisinsDispo(plateau,source);
        for (int k=0; k<(t.length); k++){
           if (t[k] == dest){
               return true;
           }
        }
        return (false);
    }
    
    public boolean SurBonus(Terrain [] plateau, int source, int i){                    //La source actuelle est-elle sur une case bonus
        int nb_villages = Tools.countVillage(plateau); //nombre de villages sur le plateau
        int ere_actuelle = Tools.age(nb_villages + i);  //age actuel
        
        String nature_case = plateau[source].getType(); // "foret", "montagne",...
                    
        
        
            String s = plateau[source].getType();
            if(s.equals("foret") && ere_actuelle <= 4 ){
                return true  ;
            }
            else if(s.equals("montagne") && ere_actuelle <= 7 && ere_actuelle >=5 ){
                                
                return true  ;
            }
            else if(s.equals("champ") && ere_actuelle <= 9 && ere_actuelle >=8){
                                
                return true  ;
            }
            else if(s.equals("plaine") && ere_actuelle <= 11 && ere_actuelle >=10){
                                
                return true  ;
            }
            else if(ere_actuelle >=12){
                return true;
            }
        
        return false;
    }
     
    public boolean SurMalus(Terrain [] plateau, int source){
        int nb_villages = Tools.countVillage(plateau); //nombre de villages sur le plateau
        int ere_actuelle = Tools.age(nb_villages);  //age actuel
        
        String nature_case = plateau[source].getType(); // "foret", "montagne",...
                    
        
        
            String s = plateau[source].getType();
            if(s.equals("montagne") && ere_actuelle <= 4 ){
                return true  ;
            }
            else if(s.equals("plaine") && ere_actuelle <= 7 && ere_actuelle >=5 ){
                                
                return true  ;
            }
            else if(s.equals("foret") && ere_actuelle <= 9 && ere_actuelle >=8){
                                
                return true  ;
            }
                else if(s.equals("champ") && ere_actuelle <= 11 && ere_actuelle >=10){
                                
                    return true  ;
                }
        
        return false;
    }
    
    public boolean PlusDeTroisVoisins(Terrain [] plateau, int source, int nbVoisins, int value){ // Les voisins de la case actuelle on-t-ils tous au moins value voisins
         for(int i = 0; i< nbVoisins; i++){
             int voisin = Tools.getVoisinsDispo(plateau, source)[i];
             if(Tools.getNbVoisinDispo(plateau, voisin) <= value){
                 return false;
             }
         }
         return true;
     }
     
    public int[] melange(int [] t){                                             //M�lange un tableau al�atoirement
        for(int position = t.length-1; position >=1; position --){
            int hasard = (int) Math.floor(Math.random() * (position + 1));
            int svg = t[position];
            t[position] = t[hasard];
            t[hasard] = svg;
        }
        return t;
    }
    
    public boolean test(List<Integer> integers,int valeurATrouver){
        int[] T = new int[integers.size()];
         for(int i=0;i<T.length;i++)
        {
            if(T[i]==valeurATrouver)    // On parcours le tableau et on test si la valeur recherch�e est pr�sente
            {
                return true;
            }
        }
         return false ;
    }
    
}