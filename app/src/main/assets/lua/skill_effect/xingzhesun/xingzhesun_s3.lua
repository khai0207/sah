--v3
--[[
xingzhesun
1
0
penxue
1
{default,gongji4,1,0,0,35,1.000000}
0

0

0

3
{11111,xingzhesun_texiao,4,0,0.000000,0.000000,35,0}
1
{default,gongji4_texiao,1,0,0,35,1.000000}
1
{default,480.000000,220.000000,480.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{111,xingzhesun_texiao,0,0,0.000000,0.000000,35,0}
1
{default,gongji4,1,0,0,35,1.000000}
1
{default,0.000000,0.000000,0.000000,0.000000,2,2,0,1,0.000000,0,0,0,0}
0

0

{2222,xingzhesun_texiao,3,0,0.000000,0.000000,35,0}
1
{default,gongji4_texiao1,1,0,0,35,1.000000}
0

0

0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji4",1.000000,0,35,1}}, 
pos_sequence      = {}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"xingzhesun_texiao",4,0,0.000000,0.000000,{{"gongji4_texiao",1.000000,0,35,1}},{{480.000000,220.000000,480.000000,220.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"xingzhesun_texiao",0,0,0.000000,0.000000,{{"gongji4",1.000000,0,35,1}},{{0.000000,0.000000,0.000000,0.000000,2,2,0,1,0.000000,0,0,0,0}},{},{},0},
{"xingzhesun_texiao",3,0,0.000000,0.000000,{{"gongji4_texiao1",1.000000,0,35,1}},{},{},{},0}}, 
bloodNum          = 0,
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
