--v3
--[[
meihouwang
1
0
penxue
2
{delay,qianjin,0,0,0,4,0.000000}{default,gongji3,1,0,5,119,1.000000}
1
{default,0.000000,0.000000,450.000000,220.000000,0,2,0,4,0.000000,0,0,0,0}
0

0

6
{heibing,meihouwang_texiao,2,5,0.000000,0.000000,114,0}
1
{default,gongji3_heiping,1,0,0,114,1.000000}
1
{default,450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{heidi,meihouwang_texiao,4,5,0.000000,0.000000,114,0}
1
{default,gongji3_heidi,1,0,0,114,1.000000}
1
{default,450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
1
{default,1.000000,1.000000,1.000000,1.000000,0,0,1}
0

{texiao2,meihouwang_texiao,2,5,0.000000,0.000000,54,0}
1
{default,gongji3_texiao2,1,0,0,54,1.000000}
1
{default,450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{teciao,meihouwang_texiao,2,5,0.000000,0.000000,54,0}
1
{default,gongji3_texiao,1,0,0,54,1.000000}
1
{default,450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{jingubang,meihouwang_texiao,1,5,0.000000,0.000000,103,0}
1
{default,gongji3_jingubang,1,0,0,103,1.000000}
1
{default,450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{baiping,meihouwang_texiao,2,5,0.000000,0.000000,60,0}
1
{default,gongji3_baiping,1,0,0,60,1.000000}
1
{default,450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",0.000000,0,4,0},{"gongji3",1.000000,5,119,1}}, 
pos_sequence      = {{0.000000,0.000000,450.000000,220.000000,0,2,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"meihouwang_texiao",2,5,0.000000,0.000000,{{"gongji3_heiping",1.000000,0,114,1}},{{450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"meihouwang_texiao",4,5,0.000000,0.000000,{{"gongji3_heidi",1.000000,0,114,1}},{{450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{{1.000000,1.000000,1.000000,1.000000,0,0,1}},{},0},
{"meihouwang_texiao",2,5,0.000000,0.000000,{{"gongji3_texiao2",1.000000,0,54,1}},{{450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"meihouwang_texiao",2,5,0.000000,0.000000,{{"gongji3_texiao",1.000000,0,54,1}},{{450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"meihouwang_texiao",1,5,0.000000,0.000000,{{"gongji3_jingubang",1.000000,0,103,1}},{{450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"meihouwang_texiao",2,5,0.000000,0.000000,{{"gongji3_baiping",1.000000,0,60,1}},{{450.000000,220.000000,450.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0}}, 
bloodNum          = 6,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
