def combine(orig, l, d, comb):
    if len(comb) == d:
        total = sum(comb)
        print comb
    else:
        for n in l:
            if sum(comb) + n <= 3807:
                if comb:
                    previous_pos = orig.index(comb[-1])
                    this_pos = orig.index(n)

                    if this_pos - previous_pos > 1 and
                    any(n < x for x in [orig[orig.index(comb[-1]) + 1 : orig.index(n) - 1]]):
                        continue
                comb.append(n)
                pos = l.index(n)
                combine(orig, l[index + 1:], d, comb)
                comb.remove(n)