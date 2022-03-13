from pyswip import Prolog
from datetime import datetime
import random

prolog = Prolog()
prolog.consult("prax06.pl")

huubid = ['london', 'istanbul', 'keiptaun']

euroopa = ['tallinn', 'helsinki', 'paris', 'berlin', 'riia', 'rooma']

ameerika = ['new_york', 'losangeles', 'brazil', 'washington', 'lima', 'santiago']

aafrika = ['dakar', 'kairo', 'victoria', 'bamako', 'juba', 'kampala', 'nairobi', 'tunis']

aasia = ['tokio', 'singapur', 'hongkong', 'beijing']

ajad = ['time(0, 0, 0.0)', 'time(0, 30, 0.0)',
        'time(1, 0, 0.0)', 'time(1, 35, 0.0)',
        'time(2, 0, 0.0)', 'time(2, 55, 0.0)',
        'time(3, 0, 0.0)', 'time(3, 20, 0.0)',
        'time(4, 0, 0.0)', 'time(4, 30, 0.0)',
        'time(5, 0, 0.0)', 'time(5, 45, 0.0)',
        'time(6, 0, 0.0)', 'time(6, 55, 0.0)',
        'time(7, 0, 0.0)', 'time(7, 10, 0.0)',
        'time(8, 0, 0.0)', 'time(8, 5, 0.0)',
        'time(9, 0, 0.0)', 'time(9, 30, 0.0)',
        'time(10, 0, 0.0)', 'time(10, 15, 0.0)',
        'time(11, 0, 0.0)', 'time(11, 45, 0.0)',
        'time(12, 0, 0.0)', 'time(12, 35, 0.0)',
        'time(13, 0, 0.0)', 'time(13, 25, 0.0)',
        'time(14, 0, 0.0)', 'time(14, 10, 0.0)',
        'time(15, 0, 0.0)', 'time(15, 5, 0.0)',
        'time(16, 0, 0.0)', 'time(16, 30, 0.0)',
        'time(17, 0, 0.0)', 'time(17, 35, 0.0)',
        'time(18, 0, 0.0)', 'time(18, 10, 0.0)',
        'time(19, 0, 0.0)', 'time(19, 20, 0.0)',
        'time(20, 0, 0.0)', 'time(20, 30, 0.0)',
        'time(21, 0, 0.0)', 'time(21, 40, 0.0)',
        'time(22, 0, 0.0)', 'time(22, 50, 0.0)',
        'time(23, 0, 0.0)', 'time(23, 15, 0.0)']

def lennud(lende_uhest_linnast = 2):
    for i in range(lende_uhest_linnast):
        for euroopa_linn in euroopa:
            for aafrika_linn in aafrika:
                aeg = 0
                valjumine = ""
                saabumine = ""
                while aeg == 0:
                    valjumine = random.choice(ajad)
                    saabumine = random.choice(ajad)
                    for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                        aeg = soln["Vahe"]
                reis = f"lennukiga({euroopa_linn},{aafrika_linn},{aeg},{valjumine},{saabumine})"
                prolog.assertz(reis)
                # print(reis + '.')
            for huub in huubid:
                aeg = 0
                valjumine = ""
                saabumine = ""
                while aeg == 0:
                    valjumine = random.choice(ajad)
                    saabumine = random.choice(ajad)
                    for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                        aeg = soln["Vahe"]
                reis_huubist = f"lennukiga({huub},{euroopa_linn},{aeg},{valjumine},{saabumine})"
                prolog.assertz(reis_huubist)
                # print(reis_huubist + '.')
                aeg = 0
                while aeg == 0:
                    valjumine = random.choice(ajad)
                    saabumine = random.choice(ajad)
                    for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                        aeg = soln["Vahe"]
                reis_huubi = f"lennukiga({euroopa_linn},{huub},{aeg},{valjumine},{saabumine})"
                prolog.assertz(reis_huubi)
                # print(reis_huubi + '.')
        for aafrika_linn in aafrika:
            aeg = 0
            valjumine = ""
            saabumine = ""
            while aeg == 0:
                valjumine = random.choice(ajad)
                saabumine = random.choice(ajad)
                for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                    aeg = soln["Vahe"]
            reis_huubist = f"lennukiga(keiptaun,{aafrika_linn},{aeg},{valjumine},{saabumine})"
            prolog.assertz(reis_huubist)
            # print(reis_huubist + '.')
            aeg = 0
            while aeg == 0:
                valjumine = random.choice(ajad)
                saabumine = random.choice(ajad)
                for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                    aeg = soln["Vahe"]
            reis_huubi = f"lennukiga({aafrika_linn},keiptaun,{aeg},{valjumine},{saabumine})"
            prolog.assertz(reis_huubi)
            # print(reis_huubi + '.')
        for aasia_linn in aasia:
            aeg = 0
            valjumine = ""
            saabumine = ""
            while aeg == 0:
                valjumine = random.choice(ajad)
                saabumine = random.choice(ajad)
                for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                    aeg = soln["Vahe"]
            reis_huubist = f"lennukiga(istanbul,{aasia_linn},{aeg},{valjumine},{saabumine})"
            prolog.assertz(reis_huubist)
            # print(reis_huubist + '.')
            aeg = 0
            while aeg == 0:
                valjumine = random.choice(ajad)
                saabumine = random.choice(ajad)
                for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                    aeg = soln["Vahe"]
            reis_huubi = f"lennukiga({aasia_linn},istanbul,{aeg},{valjumine},{saabumine})"
            prolog.assertz(reis_huubi)
            # print(reis_huubi + '.')
        for ameerika_linn in ameerika:
            for huub in huubid:
                aeg = 0
                valjumine = ""
                saabumine = ""
                while aeg == 0:
                    valjumine = random.choice(ajad)
                    saabumine = random.choice(ajad)
                    for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                        aeg = soln["Vahe"]
                reis_huubist = f"lennukiga({huub},{ameerika_linn},{aeg},{valjumine},{saabumine})"
                prolog.assertz(reis_huubist)
                # print(reis_huubist + '.')
                aeg = 0
                while aeg == 0:
                    valjumine = random.choice(ajad)
                    saabumine = random.choice(ajad)
                    for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                        aeg = soln["Vahe"]
                reis_huubi = f"lennukiga({ameerika_linn},{huub},{aeg},{valjumine},{saabumine})"
                prolog.assertz(reis_huubi)
                # print(reis_huubi + '.')
        for huub1 in huubid:
            for huub2 in huubid:
                if huub1 != huub2:
                    for i in range(lende_uhest_linnast):
                        aeg = 0
                        valjumine = ""
                        saabumine = ""
                        while aeg == 0:
                            valjumine = random.choice(ajad)
                            saabumine = random.choice(ajad)
                            for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                                aeg = soln["Vahe"]
                        reis_huubist = f"lennukiga({huub1},{huub2},{aeg},{valjumine},{saabumine})"
                        prolog.assertz(reis_huubist)
                        # print(reis_huubist + '.')
                        aeg = 0
                        while aeg == 0:
                            valjumine = random.choice(ajad)
                            saabumine = random.choice(ajad)
                            for soln in prolog.query(f"aegade_vahe({valjumine}, {saabumine}, Vahe)"):
                                aeg = soln["Vahe"]
                        reis_huubi = f"lennukiga({huub2},{huub1},{aeg},{valjumine},{saabumine})"
                        prolog.assertz(reis_huubi)
                        # print(reis_huubi + '.')


lennud(3)
start = datetime.now()
print('Linnu kokku: ' + str(len(list(prolog.query("lennukiga(Q,W,E,R,T)")))))
q = prolog.query("lyhim_reis(singapur, kairo, Tee, Hind)")
print(list(q))
q = prolog.query("odavaim_reis(singapur, kairo, Tee, Hind)")
print(list(q))
end = datetime.now()
aeg = end - start
print(f"aega läks: {aeg.seconds}.{aeg.microseconds} seconds")