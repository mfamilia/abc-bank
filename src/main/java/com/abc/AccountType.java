package com.abc;

import com.abc.accounts.Checking;
import com.abc.accounts.MaxiSavings;
import com.abc.accounts.Savings;

public enum AccountType {
    CHECKING {
        public Account create() {
            return new Checking();
        }
    },
    SAVINGS {
        public Account create() {
            return new Savings();
        }
    },
    MAXI_SAVINGS {
        public Account create() {
            return new MaxiSavings();
        }
    };
    public Account create() {
        return null;
    }
}
