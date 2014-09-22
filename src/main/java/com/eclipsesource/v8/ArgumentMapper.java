
package com.eclipsesource.v8;


public interface ArgumentMapper {

    public Object map(Object argument, int index);

    public static ArgumentMapper IdentityArgumentMapper = new  ArgumentMapper()  {
        @Override
        public Object map(final Object argument, final int index) {
            return argument;
        }
    };

}
