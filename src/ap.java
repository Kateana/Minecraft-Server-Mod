import java.util.Random;

public class ap extends gc {
    protected ap(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, jy.n);
        a(true);
    }

    @Override
    public dw d(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public int b() {
        return 20;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (parameq.d(paramInt1 - 1, paramInt2, paramInt3)) {
            return true;
        }
        if (parameq.d(paramInt1 + 1, paramInt2, paramInt3)) {
            return true;
        }
        if (parameq.d(paramInt1, paramInt2, paramInt3 - 1)) {
            return true;
        }
        return parameq.d(paramInt1, paramInt2, paramInt3 + 1);
    }

    @Override
    public void c(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);

        int j = i & 0x8;
        i &= 7;

        if ((paramInt4 == 2) && (parameq.d(paramInt1, paramInt2, paramInt3 + 1))) {
            i = 4;
        }
        if ((paramInt4 == 3) && (parameq.d(paramInt1, paramInt2, paramInt3 - 1))) {
            i = 3;
        }
        if ((paramInt4 == 4) && (parameq.d(paramInt1 + 1, paramInt2, paramInt3))) {
            i = 2;
        }
        if ((paramInt4 == 5) && (parameq.d(paramInt1 - 1, paramInt2, paramInt3))) {
            i = 1;
        }

        parameq.b(paramInt1, paramInt2, paramInt3, i + j);
    }

    @Override
    public void e(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (parameq.d(paramInt1 - 1, paramInt2, paramInt3)) {
            parameq.b(paramInt1, paramInt2, paramInt3, 1);
        } else if (parameq.d(paramInt1 + 1, paramInt2, paramInt3)) {
            parameq.b(paramInt1, paramInt2, paramInt3, 2);
        } else if (parameq.d(paramInt1, paramInt2, paramInt3 - 1)) {
            parameq.b(paramInt1, paramInt2, paramInt3, 3);
        } else if (parameq.d(paramInt1, paramInt2, paramInt3 + 1)) {
            parameq.b(paramInt1, paramInt2, paramInt3, 4);
        }
        g(parameq, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (g(parameq, paramInt1, paramInt2, paramInt3)) {
            int i = parameq.b(paramInt1, paramInt2, paramInt3) & 0x7;
            int j = 0;

            if ((!parameq.d(paramInt1 - 1, paramInt2, paramInt3)) && (i == 1)) {
                j = 1;
            }
            if ((!parameq.d(paramInt1 + 1, paramInt2, paramInt3)) && (i == 2)) {
                j = 1;
            }
            if ((!parameq.d(paramInt1, paramInt2, paramInt3 - 1)) && (i == 3)) {
                j = 1;
            }
            if ((!parameq.d(paramInt1, paramInt2, paramInt3 + 1)) && (i == 4)) {
                j = 1;
            }

            if (j != 0) {
                a_(parameq, paramInt1, paramInt2, paramInt3, parameq.b(paramInt1, paramInt2, paramInt3));
                parameq.d(paramInt1, paramInt2, paramInt3, 0);
            }
        }
    }

    private boolean g(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        if (!a(parameq, paramInt1, paramInt2, paramInt3)) {
            a_(parameq, paramInt1, paramInt2, paramInt3, parameq.b(paramInt1, paramInt2, paramInt3));
            parameq.d(paramInt1, paramInt2, paramInt3, 0);
            return false;
        }
        return true;
    }

    @Override
    public void a(iv paramiv, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramiv.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = (i & 0x8) > 0 ? 1 : 0;

        float f1 = 0.375F;
        float f2 = 0.625F;
        float f3 = 0.1875F;
        float f4 = 0.125F;
        if (k != 0) {
            f4 = 0.0625F;
        }

        if (j == 1) {
            a(0.0F, f1, 0.5F - f3, f4, f2, 0.5F + f3);
        } else if (j == 2) {
            a(1.0F - f4, f1, 0.5F - f3, 1.0F, f2, 0.5F + f3);
        } else if (j == 3) {
            a(0.5F - f3, f1, 0.0F, 0.5F + f3, f2, f4);
        } else if (j == 4) {
            a(0.5F - f3, f1, 1.0F - f4, 0.5F + f3, f2, 1.0F);
        }
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3, fz paramfz) {
        a(parameq, paramInt1, paramInt2, paramInt3, paramfz);
    }

    @Override
    public boolean a(eq parameq, int paramInt1, int paramInt2, int paramInt3, fz paramfz) {
        if (parameq.z) {
            return true;
        }

        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        int j = i & 0x7;
        int k = 8 - (i & 0x8);
        if (k == 0) {
            return true;
        }

        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), 0, 1);
        if (change == 0) {
            return true;
        }

        parameq.b(paramInt1, paramInt2, paramInt3, j + k);
        parameq.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);

        parameq.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.6F);

        parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
        if (j == 1) {
            parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        parameq.h(paramInt1, paramInt2, paramInt3, this.bh);

        return true;
    }

    @Override
    public void b(eq parameq, int paramInt1, int paramInt2, int paramInt3) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) > 0) {
            parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
            int j = i & 0x7;
            if (j == 1) {
                parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
            } else if (j == 2) {
                parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
            } else if (j == 3) {
                parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
            } else if (j == 4) {
                parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
            } else {
                parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
            }
        }
        super.b(parameq, paramInt1, paramInt2, paramInt3);
    }

    @Override
    public boolean b(iv paramiv, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (paramiv.b(paramInt1, paramInt2, paramInt3) & 0x8) > 0;
    }

    @Override
    public boolean d(eq parameq, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return false;
        }
        int j = i & 0x7;

        if ((j == 5) && (paramInt4 == 1)) {
            return true;
        }
        if ((j == 4) && (paramInt4 == 2)) {
            return true;
        }
        if ((j == 3) && (paramInt4 == 3)) {
            return true;
        }
        if ((j == 2) && (paramInt4 == 4)) {
            return true;
        }
        return (j == 1) && (paramInt4 == 5);
    }

    @Override
    public boolean c() {
        return true;
    }

    @Override
    public void a(eq parameq, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        if (parameq.z) {
            return;
        }
        int i = parameq.b(paramInt1, paramInt2, paramInt3);
        if ((i & 0x8) == 0) {
            return;
        }
        // hMod: Allow button to provide power
        int change = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(this.bh, paramInt1, paramInt2, paramInt3), 1, 0);
        if (change > 0) {
            return;
        }
        parameq.b(paramInt1, paramInt2, paramInt3, i & 0x7);

        parameq.g(paramInt1, paramInt2, paramInt3, this.bh);
        int j = i & 0x7;
        if (j == 1) {
            parameq.g(paramInt1 - 1, paramInt2, paramInt3, this.bh);
        } else if (j == 2) {
            parameq.g(paramInt1 + 1, paramInt2, paramInt3, this.bh);
        } else if (j == 3) {
            parameq.g(paramInt1, paramInt2, paramInt3 - 1, this.bh);
        } else if (j == 4) {
            parameq.g(paramInt1, paramInt2, paramInt3 + 1, this.bh);
        } else {
            parameq.g(paramInt1, paramInt2 - 1, paramInt3, this.bh);
        }

        parameq.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "random.click", 0.3F, 0.5F);
        parameq.b(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2, paramInt3);
    }
}
