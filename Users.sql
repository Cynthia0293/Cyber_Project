PGDMP         (         
        {           Users    15.2    15.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24686    Users    DATABASE     �   CREATE DATABASE "Users" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Chinese (Simplified)_China.936';
    DROP DATABASE "Users";
                postgres    false            �            1259    24687    users    TABLE     x   CREATE TABLE public.users (
    username character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    24687    users 
   TABLE DATA           3   COPY public.users (username, password) FROM stdin;
    public          postgres    false    214   9       e           2606    24691    users User_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (username);
 ;   ALTER TABLE ONLY public.users DROP CONSTRAINT "User_pkey";
       public            postgres    false    214            �   N   x�s��+��L�t��3200��
JL�H��P!���LN�:g��r�I�@v~v>'T�2�ӹ������@�+F��� �;�     